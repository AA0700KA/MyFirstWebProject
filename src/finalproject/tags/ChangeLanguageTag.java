package finalproject.tags;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Chose language tag that paste url current form page
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class ChangeLanguageTag extends TagSupport implements DynamicAttributes {

    private final Logger LOGGER = Logger.getLogger(ChangeLanguageTag.class);

    /**
     * Current page url
     */

    private String url;

    /**
     * Check servlet it or jsp
     *
     * @param string
     *             url
     */

    private boolean matchesServlet(String string) {
        Pattern pattern = Pattern.compile("[/][A-Za-z0-9]{1,}\\?([A-Za-z0-9]{1,}=[A-Za-z0-9]{1,}&{0,1})+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * Check servlet without parameters
     *
     * @param string
     *              url
     */

    private boolean matchesEmptyServlet(String string) {
        Pattern pattern = Pattern.compile("[/][A-Za-z0-9]{1,}");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    @Override
    public int doStartTag() throws JspException {
        LOGGER.info("Start language tag " + url);
        StringBuilder stringBuilder = new StringBuilder();
        String path;

        if (matchesServlet(url)) {
            path = url.split("\\?")[0];
        } else if (matchesEmptyServlet(url)) {
            path = url;
        } else {
            path = "/forward";
        }

        stringBuilder.append("<div align=\"center\" id = \"language-form\">")
                .append("<form action=\"").append(path).append("\" method=\"post\">")
                .append("<select name=\"language\">")
                .append("<option value=\"en-EN\">English</option>")
                .append("<option value=\"ru-RU\">Русский</option>")
                .append("</select>")
                .append("<input type=\"hidden\" name=\"action\" value=\"change_language\"/>")
                .append("<input type=\"hidden\" name=\"this_path\" value=\"").append(url).append("\"/>")
                .append("<input type=\"submit\" name=\"submit\" value=\"Chose\"/>")
                .append("</form>")
                .append("</div>");

        LOGGER.info(stringBuilder);

        try {
            pageContext.getOut().write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    @Override
    public void setDynamicAttribute(String url, String name, Object value) throws JspException {
        LOGGER.info("URL language " + value);
        this.url = (String) value;
    }

}
