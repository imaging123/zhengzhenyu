package org.zzy.mxrecord

/**
 * Client
 *
 * @author zzy
 * @date 2016/1/30
 */
class Client {
    static MXList mxl

    public static void main(String[] args) throws Exception{
        mxl = MXList.getInstance("dns://dns01390.ny.jeffcorp.com","jeffcorp.com")

        for(int i = 0;i<mxl.size();i++){
            println (i+1)+")priority="+((MailServer)mxl.elementAt(i)).priority+",Name="+((MailServer)mxl.elementAt(i)).server
        }

    }
}
