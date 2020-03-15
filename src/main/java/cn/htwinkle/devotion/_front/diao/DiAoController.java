package cn.htwinkle.devotion._front.diao;

import cn.htwinkle.devotion.base.BaseController;
import cn.htwinkle.devotion.constants.Constants;
import cn.htwinkle.devotion.interceptor.DiAoInterceptor;
import cn.htwinkle.devotion.kit.EhcacheKit;
import cn.htwinkle.devotion.model.User;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;

/**
 * 黄丹的需求
 *
 * @author : twinkle
 * @date : 2020/3/15 14:06
 */
@Before(DiAoInterceptor.class)
public class DiAoController extends BaseController {

    @Inject
    DiAoService service;

    @Override
    public void index() {
        setTitle("地奥制药");
        setAttr("allFile", service.getAllFileFromDiAoPath());
        render("index.html");
    }

    @Clear(DiAoInterceptor.class)
    public void login() {
        setTitle("地奥制药");
        render("login.html");
    }

    public void logOut() {
        String cookies = getCookie(Constants.SESSION__DIAO_KEY);
        EhcacheKit.INSTANCE.removeValue(cookies, User.class);
        forwardAction("/diao/login");
    }

    @Clear(DiAoInterceptor.class)
    public void toLogin(String admin, String pass) {
        User user = service.toLogin(admin, pass);
        if (user == null) {
            setMsgTip("用户名或者密码错误");
            forwardAction("/diao/login");
            return;
        }

        successToLogin(user);
        setMsgTip(String.format("欢迎您，%s ", user.getUserNickname()));
        redirect("/diao");
    }

    @Clear(DiAoInterceptor.class)
    public void uploadFile() {
        UploadFile uploadFile = getFile("uploadFile");
        if (uploadFile == null) {
            renderJson(Ret.fail("msg", "文件为空"));
            return;
        }

        String isApi = get("isApi");

        if (StrKit.notBlank(isApi)) {
            renderUploadFileAsApi(uploadFile);
        } else {
            renderUploadFileAsHtml(uploadFile);
        }
    }


    @Override
    @Clear(DiAoInterceptor.class)
    public void api() {
        renderDefaultJson();
    }

    private void successToLogin(User user) {
        setSessionUser(user);
        String vaString = StrKit.getRandomUUID();
        setCookie(Constants.SESSION__DIAO_KEY, vaString, Constants.MAX_SESSION_DATE);
        EhcacheKit.INSTANCE.saveValue(vaString, user);
    }

    private void renderUploadFileAsApi(UploadFile uploadFile) {
        if (service.fileRenameToDiAoPath(uploadFile.getFile())) {
            renderJson(Ret
                    .ok("msg", "上传成功！")
                    .set("fileName", uploadFile.getFileName())
                    .set("path", uploadFile.getFile().getAbsolutePath()));
        } else {
            renderJson(Ret
                    .fail("msg", "上传成功,但是移动失败！")
                    .set("fileName", uploadFile.getFileName())
                    .set("path", uploadFile.getFile().getAbsolutePath()));
        }
    }

    private void renderUploadFileAsHtml(UploadFile uploadFile) {
        if (service.fileRenameToDiAoPath(uploadFile.getFile())) {
            setMsgTip("上传成功！");
        } else {
            setMsgTip("上传失败！");
        }
        forwardAction("/diao");
    }


}