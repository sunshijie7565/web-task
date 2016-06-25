package WebProject.user.dao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import WebProject.user.domain.User;

/*
 * 相当于数据库
 */

public class UserDao {
	private String path ="E:/user.xml";

	public User findByUsername(String username) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(path);
			Element ele = (Element)doc.selectSingleNode("//user[@username='" + username + "']");
			if(ele == null) {
				return null;
			}
			User user = new User();
			String attrUsername = ele.attributeValue("username");
			String attrPassword = ele.attributeValue("password");
			user.setUsername(attrUsername);
			user.setPassword(attrPassword);
			return user;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	public void add(User user) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(path);
			
			Element root = doc.getRootElement();
			Element userEle = root.element("user");
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());
			
			// 创建格式化器，使用\t缩进，添加换行
			OutputFormat format = new OutputFormat("\t", true);
			// 清空数据中原有的换行
			format.setTrimText(true);
			// 创建XML输出流对象
			
			
			
			XMLWriter writer;
			try{
				writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"),format);
				// 输出Document
				writer.write(doc);
				// 关闭流
				writer.close();
			
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		}
			catch (DocumentException e)
			{
				throw new RuntimeException(e);
			}
	}
}
