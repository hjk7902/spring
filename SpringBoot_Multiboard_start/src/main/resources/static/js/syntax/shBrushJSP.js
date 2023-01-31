/**
 * SyntaxHighlighter
 * http://alexgorbatchev.com/SyntaxHighlighter
 *
 * SyntaxHighlighter is donationware. If you are using it, please donate.
 * http://alexgorbatchev.com/SyntaxHighlighter/donate.html
 *
 * @version
 * 3.0.83 (July 02 2010)
 * 
 * @copyright
 * Copyright (C) 2004-2010 Alex Gorbatchev.
 *
 * @license
 * Dual licensed under the MIT and GPL licenses.
 * 
 * @author 
 * JinKyoung Heo
 */
;(function()
{
	// CommonJS
	typeof(require) != 'undefined' ? SyntaxHighlighter = require('shCore').SyntaxHighlighter : null;

	function Brush()
	{
		var jspkeywords	=	'request response session out application page pageContext exception config ' +
		'taglib import include';
		
		var keywords =  'abstract continue for new switch assert default goto package synchronized ' + 
			'boolean do if private this break double implements protected throw ' + 
			'byte else import public throws case enum instanceof return transient ' + 
			'catch extends int short try char final interface static void ' + 
			'class finally long strictfp volatile const float native super while';

		function process(match, regexInfo)
		{
			
			var constructor = SyntaxHighlighter.Match,
				code = match[0],
				tag = new XRegExp('(&lt;|<)[\\s\\/\\?]*(?<name>[:\\w-\\.]+)', 'xg').exec(code),
				result = []
				;
		
			if (match.attributes != null) 
			{
				var attributes,
					regex = new XRegExp('(?<name> [\\w:\\-\\.]+)' +
										'\\s*=\\s*' +
										'(?<value> ".*?"|\'.*?\'|\\w+)',
										'xg');

				while ((attributes = regex.exec(code)) != null) 
				{
					result.push(new constructor(attributes.name, match.index + attributes.index, 'color1'));
					result.push(new constructor(attributes.value, match.index + attributes.index + attributes[0].indexOf(attributes.value), 'string'));
				}
			}

			if (tag != null)
				result.push(
					new constructor(tag.name, match.index + tag[0].indexOf(tag.name), 'keyword')
				);

			return result;
		}
	
		this.regexList = [
			{ regex: SyntaxHighlighter.regexLib.singleLineCComments,	css: 'comments' },			// one line comments //
			{ regex: SyntaxHighlighter.regexLib.multiLineCComments,		css: 'comments' },			// multiline comments /* ... */
			{ regex: SyntaxHighlighter.regexLib.xmlComments,												css: 'comments' },	// <!-- ... -->
			{ regex: new XRegExp('(\\&lt;|<)%--\\[[\\w\\s]*?\\[(.|\\s)*?\\]\\]%(\\&gt;|>)', 'gm'),		css: 'comments' },	// <%-- ... %>

			{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },		// strings
			{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },		// strings
			{ regex: /\b([\d]+(\.[\d]+)?|0x[a-f0-9]+)\b/gi,				css: 'value' },			// numbers
			
			{ regex: new XRegExp('(\\&lt;|<)%@[[\\w\\s]*?\\[(.|\\s)*?\\]\\]%(\\&gt;|>)', 'gm'),			css: 'keyword' },	// <%@ ... %>
			{ regex: new XRegExp('(\\&lt;|<)%[[\\w\\s]*?(\\&lt;|<)(\\s)*?(\\&gt;|>)\\[[\\w\\s]*?%(\\&gt;|>)', 'gm'),			css: 'keyword' },	// <% ... < ... > ... %>>

			{ regex: new RegExp(this.getKeywords(jspkeywords), 'gmi'),										css: 'functions' },
			{ regex: new RegExp(this.getKeywords(keywords), 'gm'),											css: 'keyword' },	// keyword
			{ regex: new XRegExp('[$]\\{[[\\w\\s]*?\\[(.|\\s)*?\\]\\]\\}', 'gmi'),							css: 'functions' },	// ${ }
			{ regex: new XRegExp('(&lt;|<)%[\\s\\/\\?]*(\\w+)(?<attributes>.*?)[\\s\\/\\?]*%(&gt;|>)', 'sg'), func: process },
			{ regex: new XRegExp('(&lt;|<)[\\s\\/\\?]*(\\w+)(?<attributes>.*?)[\\s\\/\\?]*(&gt;|>)', 'sg'), func: process }
		];
	};

	Brush.prototype	= new SyntaxHighlighter.Highlighter();
	Brush.aliases	= ['jsp', 'JSP'];

	SyntaxHighlighter.brushes.JSP = Brush;

	// CommonJS
	typeof(exports) != 'undefined' ? exports.Brush = Brush : null;
})();
