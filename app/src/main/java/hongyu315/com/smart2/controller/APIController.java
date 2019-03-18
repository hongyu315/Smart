package hongyu315.com.smart2.controller;

/**
 * <p>文件描述：<p>
 * <p>作者：rain<p>
 * <p>创建时间：2019/2/14<p>
 * <p>更改时间：2019/2/14<p>
 * <p>版本号：1<p>
 */
public class APIController {

    private static class  InnerClass{
        private static final APIController controller = new APIController();
    }

    private APIController(){
    }

    public static APIController getInstance(){
        return InnerClass.controller;
    }

}
