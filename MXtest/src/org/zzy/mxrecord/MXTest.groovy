package org.zzy.mxrecord

import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.Attribute
import javax.naming.directory.Attributes
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext

/**
 *
 * Created by imaging on 2016/1/23.
 */
class MXTest {
    public static void main(String[] args) {
        Hashtable env = new Hashtable()
        env.put("java.naming.provider.url","dns://dns01390.ny.jeffcorp.com")
        env.put("java.naming.factory.initial","com.sun.jndi.dns.DnsContextFactory")
        try {
            //创建环境对象
            DirContext dirContext = new InitialDirContext(env)
            //都区环境对象属性
            Attributes attrs = dirContext.getAttributes("jeffcorp.com",["MX"] as String[])
            for (NamingEnumeration ae=attrs.getAll();ae != null && ae.hasMoreElements();){
                Attribute attr = (Attribute)ae.next()
                NamingEnumeration e = attr.getAll()

                while(e.hasMoreElements()){
                    String element = e.nextElement().toString()
                    println element
                }
            }
        }catch (NamingException e){
            e.printStackTrace()
        }

    }

}
