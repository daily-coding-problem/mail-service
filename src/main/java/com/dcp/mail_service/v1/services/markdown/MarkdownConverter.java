package com.dcp.mail_service.v1.services.markdown;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.List;
import java.util.Objects;

public class MarkdownConverter {
	// Enum to define the output type
	public enum OutputType {
		HTML
		// Other types can be added here in the future (e.g., PDF, PlainText)
	}

	public static String convertMarkdown(String markdown, OutputType outputType) {
		if (Objects.requireNonNull(outputType) == OutputType.HTML) {
			return convertToHtml(markdown);
		}

		throw new IllegalArgumentException("Unsupported output type: " + outputType);
	}

	private static String convertToHtml(String markdown) {
		// Setup options for the parser and renderer
		MutableDataSet options = new MutableDataSet();
		options.set(Parser.EXTENSIONS, List.of());

		// Parse the Markdown content
		Parser parser = Parser.builder(options).build();
		Node document = parser.parse(markdown);

		// Render the document to HTML
		HtmlRenderer renderer = HtmlRenderer.builder(options).build();
		return renderer.render(document);
	}
}
