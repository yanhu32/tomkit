package yh.tomkit.core.string;

/**
 * @author yh
 * @since 2021/1/29
 */
interface StringInterface {

    /**
     * 空字符串
     */
    String EMPTY = "";

    /**
     * 下划线
     */
    char UNDERLINE = '_';

    /**
     * 双引号
     */
    char DOUBLE_QUOTE = '\"';

    /**
     * 单引号（英文）
     */
    char QUOTE = '\'';

    /**
     * 空格字符
     */
    char SPACE = ' ';

    /**
     * 空格
     */
    String SPACE_STR = " ";

    /**
     * 省略号
     */
    String ELLIPSIS = "...";

    /**
     * 表示搜索失败的索引
     */
    int INDEX_NOT_FOUND = -1;

    /**
     * 填充常数可以扩展到的最大大小
     */
    int PAD_LIMIT = 8192;

}
