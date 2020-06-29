package com.example.wkl_android.http.utils;

/**
 * @author li
 * @since 2019/1/7
 */
public class JsonFormat {
    /**
     * json字符串格式化
     *
     * @param jsonStr json数据
     * @return 格式化的json数据
     */
    public static String format(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        // 前一个引号是不是后引号
        boolean hasPostQuote = true;
        char last;
        char current = '\0';
        int indent = 0;
        int length = jsonStr.length();
        for (int i = 0; i < length; i++) {
            last = current;
            current = jsonStr.charAt(i);
            //遇到{ [换行，且下一行缩进
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                //遇到} ]换行，当前行缩进
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                //遇到,换行
                case ',':
                    sb.append(current);
                    if (last != '\\' && hasPostQuote) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '\"':
                    hasPostQuote = !hasPostQuote;
                    sb.append('\"');
                    break;
                default:
                    sb.append(current);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 添加跳格
     *
     * @param sb     原数据
     * @param indent 缩进量
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
