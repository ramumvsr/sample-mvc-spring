package com.qv2mobileweb;


import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class XPathGenerator {

	private static final Logger LOG = Logger.getLogger(XPathGenerator.class);
	private boolean hasAttributes = false;

	public String getElementName(Element element) {
		LOG.info("Generating ElementName Start ");
		String elementName = "";

		try {
			String tagName = element.tagName().toLowerCase();

			if (element.hasAttr("id")) {
				elementName = tagName + "-" + element.attr("id");

			} else if (element.hasAttr("class")) {
				elementName = tagName + "-" + element.attr("class");
			} else if (element.hasAttr("name")) {
				elementName = tagName + "-" + element.attr("name");
			} else if (element.hasText()) {
				elementName = tagName + "-" + element.text().split(" ")[0];
			} else {
				elementName = tagName;
			}

		} catch (Exception e) {
			LOG.error("Failed in generating ElementName : " + e);
		}

		LOG.info("Generating ElementName End ");

		return elementName;

	}

	public String getAbsloutePath(Element element) {
		LOG.info("Generating AbsloutePath Start ");
		String absloutePath = "";

		try {

			if (!element.attr("id").equals("")) {
				absloutePath = "//" + element.tagName().toLowerCase()
						+ "[@id='" + element.id() + "']";

			}

			if (element.tagName().toLowerCase() == "body") {
				absloutePath = element.tagName().toLowerCase();
			}

			int index = 0;
			List<Node> siblings = element.parentNode().childNodes();

			for (int i = 0; i < siblings.size() - 1; i++) {
				Node node = siblings.get(i);
				if (node == element) {
					absloutePath = getAbsloutePath((Element) element
							.parentNode())
							+ '/'
							+ element.tagName().toLowerCase()
							+ '['
							+ (index + 1) + ']';

				}

				if (node.nodeName().equalsIgnoreCase(element.tagName())) {
					index++;
				}

			}

		} catch (Exception e) {

			LOG.error("Failed in generating AbsloutePath: " + e);

		}

		LOG.info("Generating AbsloutePath End ");

		return absloutePath;

	}

	public String getRelativePath(Element element) {
		LOG.info("Generating Relative XPath Start ");
		String relativePath = "";

		try {

			String tagName = element.tagName().toLowerCase();
			String innerText = element.text();
			relativePath = ("(//") + (tagName);
			relativePath += ("[");

			if (!element.attr("id").equals("")) {
				relativePath += ("@id='") + (element.attr("id")) + ("'])");

			}
			
			
			
			
			

		} catch (Exception e) {

			LOG.error("Failed in generating Relative Xpath: " + e);
		}

		LOG.info("Generating Relative Xpath End ");
		return relativePath;

	}

}
