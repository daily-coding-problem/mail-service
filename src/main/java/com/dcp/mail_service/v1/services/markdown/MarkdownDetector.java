package com.dcp.mail_service.v1.services.markdown;

import java.util.regex.Pattern;

public class MarkdownDetector {
	// Regular expressions for common Markdown syntax patterns
	private static final Pattern HEADER_PATTERN = Pattern.compile("^(#{1,6})\\s+.+$", Pattern.MULTILINE);
	private static final Pattern LIST_PATTERN = Pattern.compile("^\\s*[-*+]\\s+.+$", Pattern.MULTILINE);
	private static final Pattern NUMBERED_LIST_PATTERN = Pattern.compile("^\\s*\\d+\\.\\s+.+$", Pattern.MULTILINE);
	private static final Pattern LINK_PATTERN = Pattern.compile("\\[.+?\\]\\(.+?\\)");
	private static final Pattern IMAGE_PATTERN = Pattern.compile("!\\[.*?\\]\\(.*?\\)");
	private static final Pattern EMPHASIS_PATTERN = Pattern.compile("([_*]{1,2}).+?\\1");
	private static final Pattern CODE_PATTERN = Pattern.compile("(`{1,3}).+?\\1");
	private static final Pattern BLOCKQUOTE_PATTERN = Pattern.compile("^>\\s+.+$", Pattern.MULTILINE);
	private static final Pattern HORIZONTAL_RULE_PATTERN = Pattern.compile("^\\s*(([-*_])\\s*\\2\\s*\\2\\s*)\\s*$", Pattern.MULTILINE);

	public static boolean isMarkdown(String text) {
		return HEADER_PATTERN.matcher(text).find() ||
			LIST_PATTERN.matcher(text).find() ||
			NUMBERED_LIST_PATTERN.matcher(text).find() ||
			LINK_PATTERN.matcher(text).find() ||
			IMAGE_PATTERN.matcher(text).find() ||
			EMPHASIS_PATTERN.matcher(text).find() ||
			CODE_PATTERN.matcher(text).find() ||
			BLOCKQUOTE_PATTERN.matcher(text).find() ||
			HORIZONTAL_RULE_PATTERN.matcher(text).find();
	}
}
