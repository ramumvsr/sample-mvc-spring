package com.qv2mobileweb;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ElementExtractor {

	/*private static String Appium_Node_Path = "Appium/node.exe";
	private static String Appium_JS_Path = "Appium/node_modules/appium/bin/appium.js";
	private static AppiumDriverLocalService appiumService;
	private static String appiumServiceUrl;*/
	//private static AppiumDriverLocalService appiumService;
	private RemoteWebDriver driver = null;
	private static ElementExtractor elementExtractor = null;

	private String[] allowedTags = { "input", "textarea", "h1", "h2", "h3",
			"h4", "h5", "h6", "button", "a", "img", "span", "div", "link",
			"select", "label", "option", "iframe", "th", "table", "ul",
			"header", "p", "checkbox", "i", "b", "strong", "em", "mark",
			"small", "del", "ins", "sub", "sup", "code", "var", "samp", "kdb",
			"ol", "nav", "section" };

	private Map<String, List<String>> finalElements = new HashMap<>();

	private List<String> textfields = new ArrayList<>();
	private List<String> buttons = new ArrayList<>();
	private List<String> links = new ArrayList<>();
	private List<String> spans = new ArrayList<>();
	private List<String> div = new ArrayList<>();
	private List<String> images = new ArrayList<>();
	private List<String> select = new ArrayList<>();
	private List<String> checkboxes = new ArrayList<>();
	private List<String> label = new ArrayList<>();
	private List<String> dropdown = new ArrayList<>();
	private List<String> imagebutton = new ArrayList<>();
	private List<String> optionbutton = new ArrayList<>();
	private List<String> textarea = new ArrayList<>();
	private List<String> iframe = new ArrayList<>();
	private List<String> tableheader = new ArrayList<>();
	private List<String> alert = new ArrayList<>();
	private List<String> filebrowser = new ArrayList<>();
	private List<String> table = new ArrayList<>();
	private List<String> unorderedlist = new ArrayList<>();
	private List<String> header = new ArrayList<>();
	private List<String> filemanager = new ArrayList<>();
	private List<String> design = new ArrayList<>();
	private List<String> orderedList = new ArrayList<>();
	private List<String> paragraph = new ArrayList<>();
	private List<String> radiobutton = new ArrayList<>();
	private List<String> nav = new ArrayList<>();
	private List<String> section = new ArrayList<>();

	/**
	 *
	 * @param browserName
	 * @param deviceName
	 * @param platformVersion
	 * @param platformName
	 */
	private ElementExtractor(String browserName, String deviceName,
			String platformVersion, String platformName) {
		this.setDesiredCapabilities(browserName, deviceName, platformVersion,
				platformName);
	}

	/**
	 * This method create single object for ElementExtractor
	 * 
	 * @param browserName
	 * @param deviceName
	 * @param platformVersion
	 * @param platformName
	 * @return
	 */
	public static synchronized ElementExtractor getInstance(String browserName,
			String deviceName, String platformVersion, String platformName) {

		if (elementExtractor == null) {
			elementExtractor = new ElementExtractor(browserName, deviceName,
					platformVersion, platformName);
		}

		return elementExtractor;

	}

	/**
	 * This method sets the desiredCapabilities
	 * 
	 * @param browserName
	 * @param deviceName
	 * @param platformVersion
	 * @param platformName
	 */
	public void setDesiredCapabilities(String browserName, String deviceName,
			String platformVersion, String platformName) {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);
		startAppiumServer(capabilities);

	}

	/**
	 * This method starts the appium server and make a session
	 * 
	 * @param capabilities
	 */
	public void startAppiumServer(DesiredCapabilities capabilities) {

		try {
			/*appiumService = AppiumDriverLocalService
					.buildService(new AppiumServiceBuilder().usingAnyFreePort()
							.usingDriverExecutable(new File(Appium_Node_Path))
							.withAppiumJS(new File(Appium_JS_Path)));
			appiumService.start();*/

			driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (Exception e) {

			System.out.println("Failed in Starting Appium Server: "
					+ e.getMessage());
		}

	}

	/**
	 * This method extracts elements from mobile web page.
	 * 
	 * @param url
	 * @return a Map object which contain elements
	 */
	public Map<String, List<String>> extarctElements(String url) {
		Document doc=null;
		try {
			driver.get(url);

			Thread.sleep(5000);

			doc = Jsoup.connect(url).get();
			Elements mobileWebElements = doc.body().select("*");

			if (mobileWebElements != null) {

				for (Element element : mobileWebElements) {
					String tagName = element.tagName().toLowerCase();
					for (String tag : allowedTags) {
						if (tag.equalsIgnoreCase(tagName)) {

							finalElements = traverseElements(element);

						}
					}

				}

			}

		} catch (IOException | InterruptedException e) {
			System.out.println("Failed in Extracting elements :" + e);
		}

		return finalElements;
	}

	/**
	 * This method traverse through element to generate Xpath
	 * 
	 * @param element
	 * @return a Map object which contain elements
	 */
	public Map<String, List<String>> traverseElements(Element element) {

		XPathGenerator xPathGenerator = new XPathGenerator();
		String absloutePath = xPathGenerator.getAbsloutePath(element);
		String elementName = xPathGenerator.getElementName(element);
		xPathGenerator.getRelativePath(element);

		String tagName = element.tagName().toLowerCase();

		switch (tagName) {
		case "input":
			if (element.attr("type").equalsIgnoreCase("text")
					|| element.attr("type").equalsIgnoreCase("email")
					|| element.attr("type").equalsIgnoreCase("password")) {
				textfields.add(absloutePath);
			} else if (element.attr("type").equalsIgnoreCase("submit")
					|| element.attr("type").equalsIgnoreCase("button")) {
				buttons.add(absloutePath);
			}
			break;
		case "button":
			buttons.add(absloutePath);
			break;
		case "a":
			links.add(absloutePath);
			break;
		case "img":
			images.add(absloutePath);
			break;
		case "span":
			spans.add(absloutePath);
			break;
		case "checkbox":
			checkboxes.add(absloutePath);
			break;
		case "div":
			div.add(absloutePath);
			break;
		case "select":
			select.add(absloutePath);
			break;
		case "label":
			label.add(absloutePath);
			break;
		case "textarea":
			textarea.add(absloutePath);
			break;
		case "link":
			links.add(absloutePath);
			break;
		case "option":
			dropdown.add(absloutePath);
			break;
		case "iframe":
			iframe.add(absloutePath);
			break;
		case "th":
			tableheader.add(absloutePath);
			break;
		case "table":
			table.add(absloutePath);
			break;
		case "ul":
			unorderedlist.add(absloutePath);
			break;
		case "header":
		case "h1":
		case "h2":
		case "h3":
		case "h4":
		case "h5":
		case "h6":
			header.add(absloutePath);
			break;
		case "p":
			paragraph.add(absloutePath);
			break;
		case "ol":
			orderedList.add(absloutePath);
			break;
		case "i":
		case "b":
		case "strong":
		case "em":
		case "mark":
		case "small":
		case "del":
		case "ins":
		case "sub":
		case "sup":
		case "code":
		case "var":
		case "samp":
		case "kdb":
			design.add(absloutePath);
			break;
		case "nav":
			nav.add(absloutePath);

		case "section":
			section.add(absloutePath);

		}

		finalElements.put("textfields", textfields);
		finalElements.put("links", links);

		finalElements.put("buttons", buttons);

		finalElements.put("spans", spans);
		finalElements.put("div", div);
		finalElements.put("images", images);
		finalElements.put("select", select);
		finalElements.put("checkboxes", checkboxes);
		finalElements.put("label", label);
		finalElements.put("dropdown", dropdown);
		finalElements.put("imagebutton", imagebutton);
		finalElements.put("optionbutton", optionbutton);
		finalElements.put("textarea", textarea);
		finalElements.put("iframe", iframe);
		finalElements.put("tableheader", tableheader);
		finalElements.put("alert", alert);
		finalElements.put("filebrowser", filebrowser);
		finalElements.put("table", table);
		finalElements.put("unorderedlist", unorderedlist);
		finalElements.put("header", header);
		finalElements.put("filemanager", filemanager);
		finalElements.put("design", design);
		finalElements.put("orderedList", orderedList);
		finalElements.put("paragraph", paragraph);
		finalElements.put("radiobutton", radiobutton);
		finalElements.put("nav", nav);
		finalElements.put("section", section);
		return finalElements;

	}

	/**
	 * This method highlightElements the particular element by identifying with
	 * xpath
	 * 
	 * @param xpath
	 */
	public void highlightElement(String xpath) {

		try {
			WebElement element = driver.findElementByXPath(xpath);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("var css = '.highlistelement{"
					+ "outline:2px solid #F70B0B !important;"
					+ "border:2px solid red !important;"
					+ "-webkit-box-shadow:0 0 0 2px #F70B0B !important, 0 0 0 2px #F70B0B !important;"
					+ "}',"
					+ "head = document.getElementsByTagName('head')[0],style = document.createElement('style');"
					+ "style.type = 'text/css';"
					+ "if (style.styleSheet){style.styleSheet.cssText = css;} "
					+ "else {style.appendChild(document.createTextNode(css));"
					+ "}head.appendChild(style);");

			js.executeScript(
					"function hasClass(ele,cls) {"
							+ "    return ele.className.match('highlistelement');"
							+ "}"
							+ "function addClass(ele) {"
							+ "    if(ele.length != null) {"
							+ "        for (var i = ele.length - 1; i >= 0; i--) {"
							+ "            if (!hasClass(ele[i],'highlistelement')) ele[i].className += ' highlistelement';"
							+ "        }"
							+ "    }else{"
							+ "        if (!hasClass(ele,'highlistelement')) ele.className += ' highlistelement';"
							+ "    }" + "}" + "addClass(arguments);", element);

		} catch (Exception e) {

			System.out
					.println("Failed in locating element : " + e.getMessage());
		}

	}

}
