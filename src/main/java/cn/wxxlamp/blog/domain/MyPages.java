package cn.wxxlamp.blog.domain;

/**
 * 前端传入数据
 * @author Zi10ng
 * @date 2019年7月26日09:29:18
 */
public class MyPages {
    /**
     * 每页有多少条
     */
    private int size;
    /**
     * 第几页
     */
    private int page;

    /**
     * 其他信息
     */
    private Object others;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "MyPages{" +
                "size=" + size +
                ", page=" + page +
                ", others=" + others +
                '}';
    }
}
