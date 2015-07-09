package com.lzx.framework.socket;


public class SocketMessage {

		protected Integer cmd;
		protected IMessage msgBody;
		
		public SocketMessage(int cmd, IMessage msgBody) {
			this.cmd = cmd;
			this.msgBody = msgBody;
		}
		
		public SocketMessage(int cmd){
			this.cmd=cmd;
		}
		
		public SocketMessage() {
		}


		public Integer getCmd() {
			return cmd;
		}

		public void setCmd(int cmd) {
			this.cmd = cmd;
		}
		
		public IMessage getMsgBody() {
			return msgBody;
		}
		public void setMsgBody(IMessage msgBody) {
			this.msgBody = msgBody;
		}

		@Override
		public String toString() {
			return "Message [cmd=" + cmd + ", msgBody=" + msgBody + "]";
		}
}
