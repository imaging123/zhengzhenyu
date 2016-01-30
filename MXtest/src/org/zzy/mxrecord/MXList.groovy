package org.zzy.mxrecord

import javax.naming.NamingEnumeration
import javax.naming.directory.Attribute
import javax.naming.directory.Attributes
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext

/**
 * MXList:单例类，负责查询和保存列表
 *
 * @author zzy
 * @date 2016/1/30
 */
class MXList {
    static MXList mxl = null
    Vector list = null
    static final String FACTORY_ENTRY = "java.naming.factory.initial"
    static final String FACTORY_CLASS = "com.sun.jndi.dns.DnsContextFactory"
    static final String PROVIDER_ENTRY = "java.naming.provider.url"
    static final String MX_TYPE = "MX"
    String dnsUrl = null
    String domainName = null

    //默认私有构造子
    private MXList(){
    }
    private MXList(String dnsUrl,String domainName){
        this.dnsUrl = dnsUrl
        this.domainName = domainName
    }

    //提供本类唯一的实例
     static synchronized MXList getInstance( String dnsUrl,String domainName){
       if(!mxl){
           mxl = new MXList(dnsUrl,domainName)
       }
        mxl
    }

    /**
     * 聚集方法，提供聚集元素
     * @param index
     * @return
     * @throws Exception
     */
    MailServer elementAt(int index) throws Exception{
        return (MailServer)list.elementAt(index)
    }

    /**
     * 聚集方法，提供聚集大小
     * @return
     */
    int size(){
        return list.size()
    }

    /**
     * 辅助方法，向DNS服务器查询MX记录
     * @param dnsUrl
     * @param domainName
     * @return
     * @throws Exception
     */
    private Vector getMXRecords(String provideUrl,String domainName) throws Exception{

        //设置环境性质
        Hashtable env = new Hashtable()
        env.put(FACTORY_ENTRY,FACTORY_CLASS)
        env.put(PROVIDER_ENTRY,provideUrl)

        //创建环境对象
        DirContext dirContext = new InitialDirContext(env)

        Vector records = new Vector(10)

        //读取环境对象的属性
        Attributes attrs = dirContext.getAttributes(domainName,[MX_TYPE] as String[])

        for(NamingEnumeration ne:attrs.getAll()){
            Attribute attr = (Attribute)ne.next()
            NamingEnumeration e = attr.getAll()
            while(e.hasMoreElements()){
                String element = e.nextElement().toString()
                StringTokenizer tokenizer = new StringTokenizer(element," ")

                MailServer mailServer = new MailServer()

                String token1 = tokenizer.nextToken()
                String token2 = tokenizer.nextToken()

                if(token1 && token2){
                    mailServer.setProperty(Integer.valueOf(token1).intValue())
                    mailServer.server = token2
                    records.addElement(mailServer)
                }

            }

        }
        println "List created."

        return records
    }

}
