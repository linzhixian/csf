package com.lzx.framework.socket;

public interface Length {

    /**
     * Integer型数据的长度
     */
    int INT4 = 4;

    int SHORT2 = 2;
    
    /**
     * 消息结构：消息头(数据包长度+请求标识)+消息体+MAC(MAC+CRC)
     */

    // 消息头：数据包长度
    int PACKET_LENGTH = SHORT2;

    // 消息头：请求标识
    int REQUESTID_LENGTH = SHORT2;

    // 消息头长度=数据包长度+请求标识
    int HEADER_LENGTH = PACKET_LENGTH + REQUESTID_LENGTH;
    //MAC
    int DES_LENGTH = 8;
    int CRC_LENGTH = 4;

    /**
     * MAC长度
     */
    int MAC_LENGTH = CRC_LENGTH;

}
