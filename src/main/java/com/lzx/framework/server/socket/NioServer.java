package com.lzx.framework.server.socket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzx.framework.socket.CodecFactory;
import com.lzx.framework.socket.DefaultSocketListener;
import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.ISocketListener;
import com.lzx.framework.socket.ISocketServer;
import com.lzx.framework.socket.MessageDispatch;
import com.lzx.framework.socket.MessageID;
import com.lzx.framework.socket.SocketServerFactory;
import com.lzx.framework.socket.SocketServerInitializer;
import com.lzx.framework.socket.message.PingMessage;

public abstract class NioServer extends AbstractNioServer {
	private static final Logger logger = LoggerFactory.getLogger(NioServer.class);

	// 消息CommandAction Spring bean名称后缀
	public static final String Suffix_CommandAction = "_ActionLZX";
	// 消息 Spring bean名称后缀
	public static final String Suffix_MessageName = "_MessageLZX";

	ISocketServer socketServer;

	private int workThreadCount = 5;// Netty work线程数

	protected MessageDispatch messageDispath;

	public static 	Map<String, Integer> COMMANDS_IDS_MAP;
	public IMessageDispatch getMessageDispath() {
		return messageDispath;
	}

	public void setMessageDispath(MessageDispatch messageDispath) {
		this.messageDispath = messageDispath;
	}

	protected DefaultSocketListener socketListner;
	protected SessionManager sessionManager;

	@Override
	public void initBeforeStart() {
		messageDispath = new MessageDispatch();
		socketListner = new DefaultServerSocketListener(this);
		sessionManager = new SessionManager(this);
		initLoadCommands();
	}

	@Override
	public void start() {

		SocketServerFactory socketServerFactory = (SocketServerFactory) this.getApplicationContext().getBean(
				"socketServerFactory");
		socketServer = socketServerFactory.createSocketServer(createServerInitializer());
		try {
			socketServer.bind();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	protected SocketServerInitializer createServerInitializer() {
		return new SocketServerInitializer() {
			@Override
			public int getPort() {
				return NioServer.this.getPort();
			}

			@Override
			public IMessageDispatch getMessageDispatch() {
				return messageDispath;
			}

			public ISocketListener getSocketListener() {
				return socketListner;
			}

			@Override
			public int getWorkThreadCount() {
				return workThreadCount;
			}

			@Override
			public CodecFactory getCodecFactory() {

				return createCodecFactory();
			}
			@Override
			public IMessage getPingMessage() {
				return new PingMessage();
			}
		};
	}

	public CodecFactory createCodecFactory() {
		return CodecFactory.getDefault();
	}

	protected void initLoadCommands() {
		COMMANDS_IDS_MAP = loadCommandIDs();
		String[] beanNames = this.getApplicationContext().getBeanDefinitionNames();
		for (String name : beanNames) {
			if (name.endsWith(Suffix_MessageName)) {
				Object messageBean = this.getApplicationContext().getBean(name);

				if (IMessage.class.isInstance(messageBean)) {
					IMessage message = (IMessage) messageBean;
					MessageID msgid = messageBean.getClass().getAnnotation(MessageID.class);
					if (msgid != null) {
						message.setCmd(msgid.id());
					}
					if (message.getCmd() <= 0) {
						Integer cmd = COMMANDS_IDS_MAP.get(message.getClass().getSimpleName());
						if (cmd != null) {
							message.setCmd(cmd);
							logger.info("Register:" + message + ":"+cmd);
						} else {
							System.err.println("MessageBean:" + message + " not setCmd.");
							continue;
						}
					}
					String commandActionBeanName = generateCommandActionBeanName(name);
					Object commandActionBean = this.getApplicationContext().getBean(commandActionBeanName);
					if (commandActionBean == null || !ICommand.class.isInstance(commandActionBean)) {
						System.err.println("Error:not found action for " + name);
						continue;
					}
					messageDispath.registerMessage(message.getCmd(), message.getClass(), (ICommand) commandActionBean);
				}

			}
		}
	}

	private Map<String, Integer> loadCommandIDs() {
/*		URL url=this.getClass().getResource("/conf/proto");
		url.*/
		String path = this.getAppRootDir() + "/conf/proto";
		File dir = new File(this.getAppRootDir() + "/conf/proto");
		if (!dir.isDirectory()) {
			System.err.println("proto directory not exist:" + path);
			return null;
		}
		Map<String, Integer> commandids = new HashMap<String, Integer>();
		Map<Integer, String> dupCheck = new HashMap<Integer, String>();
		File[] listFiles = dir.listFiles();
		for (File f : listFiles) {
			if (!f.getName().endsWith(".proto"))
				continue;
			System.out.println("parser file:" + f.getName());
			try {
				RandomAccessFile r = new RandomAccessFile(f, "r");
				String line = null;
				int lastCmd = 0;
				boolean hasDup = false;
				while ((line = r.readLine()) != null) {
					try {
						line = line.trim();
						if (line.startsWith("//(")) {
							String cmdstr = line.substring(line.indexOf("(") + 1, line.indexOf(")")).trim();
							if (cmdstr.indexOf("-") > 0) {
								lastCmd = Integer.parseInt(cmdstr.substring(0, cmdstr.indexOf("-")));
								String cmdName = cmdstr.substring(cmdstr.indexOf("-") + 1);
								hasDup = putCmd(commandids, dupCheck, lastCmd, cmdName);
								lastCmd = 0;
							} else {
								lastCmd = Integer.parseInt(cmdstr);
							}

						} else if (line.startsWith("message") && lastCmd > 0) {
							String cmdName = line.substring(line.indexOf("message") + 7, line.indexOf("{")).trim();
							hasDup = putCmd(commandids, dupCheck, lastCmd, cmdName);
							lastCmd = 0;
						} else {
							lastCmd = 0;
						}
					} catch (Exception e) {
						System.out.println(line);
						e.printStackTrace();
					}

				}
				if (hasDup) {
					throw new RuntimeException("duplicate register cmd:" + line);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return commandids;
	}

	private boolean putCmd(Map<String, Integer> commandids, Map<Integer, String> dupCheck, int lastCmd, String cmdName) {
		if (dupCheck.containsKey(lastCmd)) {
			System.err.println("duplicate register:" + lastCmd + "-" + cmdName + ",has be as " + dupCheck.get(lastCmd));
			return true;
		}
		if (commandids.containsKey(cmdName)) {
			System.err.println("duplicate register:" + cmdName + "-" + lastCmd + ",has been as " + commandids.get(cmdName));
			return true;
		}
		commandids.put(cmdName, lastCmd);
		dupCheck.put(lastCmd, cmdName);
		System.out.println("   register:" + cmdName + "-" + lastCmd);
		return false;
	}

	// 根据约定把MessageBean 的名字转换成 commandAction的名字：chatRequest_MessageLZX
	// -->chatRequestAction_MessageLZX
	private static String generateCommandActionBeanName(String messageBeanName) {
		int index = messageBeanName.indexOf("_");
		if (index == -1)
			return "NOT FOUND FOR " + messageBeanName;
		return messageBeanName.substring(0, index) + "Action" + Suffix_CommandAction;
	}

	@Override
	public void stop() {
		if (socketServer != null) {
			socketServer.close();
		}
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public static void main(String[] args) {
		System.out.println(generateCommandActionBeanName("chatRequest_MessageLZX"));
	}
}
