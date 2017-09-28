package com.item1024.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.*;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/9/12
 */

public class XmlUtils {
// 将String转换成XML
        public static Document str2XML(String string) throws Exception {

            SAXBuilder buider = new SAXBuilder();

            Document document = buider.build(new StringReader(string));

            return document;

        }

        //将XML文件转换成String输出
        public static String xml2Str(String file) throws Exception {

            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new FileInputStream(new File(file)));

            Format format = Format.getCompactFormat();
            format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题
            XMLOutputter xmlout = new XMLOutputter();

            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            xmlout.output(document, bo);
            return bo.toString().trim();

        }

        // 将XML文件以键值对的形式输出,对于有两层以上的XML文件
        public static Map<String, String> paraseXML(String file) throws Exception {

            Map<String, String> map = new HashMap<String, String>();

            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new File(file));

            Element root = document.getRootElement();

            List<Element> list = root.getChildren();
            for (Iterator<Element> iterator = list.iterator(); iterator.hasNext();) {

                Element firstChild = iterator.next();
                List<Element> secondElements = firstChild.getChildren();

                for (Iterator<Element> iterator2 = secondElements.iterator(); iterator2
                        .hasNext();) {

                    Element secondElement = iterator2.next();
                    map.put(secondElement.getName(), secondElement.getText());
                }

            }

            return map;
        }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     *
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XmlUtils.getChildrenText(children);
            }

            m.put(k, v);
        }

        // 关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(XmlUtils.getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    /**
     * DOCUMENT格式化输出保存为XML
     *
     * @param doc JDOM的Document
     * @param filePath 输出文件路径
     * @throws Exception
     */
    public static void doc2XML(Document doc, String filePath) throws Exception{
        Format format = Format.getCompactFormat();
        format.setEncoding("UTF-8"); //设置XML文件的字符为UTF-8
        format.setIndent("     ");//设置缩进

        XMLOutputter outputter = new XMLOutputter(format);//定义输出 ,在元素后换行，每一层元素缩排四格
        FileWriter writer = new FileWriter(filePath);//输出流
        outputter.output(doc, writer);
        writer.close();
    }

    /**
     * 字符串转换为DOCUMENT
     *
     * @param xmlStr 字符串
     * @return doc JDOM的Document
     * @throws Exception
     */
    public static Document string2Doc(String xmlStr) throws Exception {
        Reader in = new StringReader(xmlStr);
        Document doc = (new SAXBuilder()).build(in);
        return doc;
    }

    /**
     * Document转换为字符串
     *
     * @param
     * @return xmlStr 字符串
     * @throws Exception
     */
    public static String doc2String(Document doc) throws Exception {
        Format format = Format.getPrettyFormat();
        format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题
        XMLOutputter xmlout = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        xmlout.output(doc, bo);
        return bo.toString();
    }

    /**
     * XML转换为Document
     *
     * @param xmlFilePath XML文件路径
     * @return doc Document对象
     * @throws Exception
     */
    public static Document xml2Doc(String xmlFilePath) throws Exception {
        File file = new File(xmlFilePath);
        return (new SAXBuilder()).build(file);
    }


    public static void main(String[] args) throws Exception {

            String str = "<students><Student><name>zhangsan</name><age>18</age><name>lisi</name><age>28</age></Student></students>";
            Document document = str2XML(str);
            Element root = document.getRootElement();
            System.out.println(root.getName());
            System.out.println(root.getChildText("name"));

            XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat()
                    .setIndent("     "));
            FileOutputStream fos = new FileOutputStream(new File(
                    "date\\string2xml.xml"));
            outputter.output(document, fos);
            fos.close();

            String xmlContent = xml2Str("date\\string2xml.xml");
            System.out.println("XML的内容为： "+ "\n" + xmlContent);

            Map<String, String> xmlMap = new HashMap<String, String>();
            xmlMap = paraseXML("date\\string2xml.xml");
            Set<String> keysSet = xmlMap.keySet();
            for(String key: keysSet){
                String value = xmlMap.get(key);
                System.out.println(key + " = " + value);
            }


        }

    }
