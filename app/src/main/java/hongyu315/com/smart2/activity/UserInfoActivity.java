package hongyu315.com.smart2.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hb.dialog.myDialog.MyAlertInputDialog;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hongyu315.com.smart2.R;
import hongyu315.com.smart2.bean.User;
import hongyu315.com.smart2.constant.Constant;
import hongyu315.com.smart2.util.DensityUtil;
import hongyu315.com.smart2.util.ImgUtil;
import hongyu315.com.smart2.util.SPUtils;
import hongyu315.com.smart2.view.pickdatetime.DatePickDialog;
import hongyu315.com.smart2.view.pickdatetime.OnSureListener;
import hongyu315.com.smart2.view.pickdatetime.bean.DateParams;

public class UserInfoActivity extends BaseActivity {

    private TitleBar mTitleBar;
    private ImageView userIcon;
    private TextView userNick;
    private TextView userSex;
    private TextView userBirthday;

    String cameraArray[] = {"从系统相册中选择","拍照"};
    String sexArray[] = {"男","女"};
    int selectedPosition = 0;
    boolean isSexLayoutPressed = false;

    private static final int CHOOSE_PHOTO=0;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_SMALL_PICTURE = 3;

    //调用照相机返回图片文件
    private File tempFile;
    //最后显示的图片文件
    private  String mFile;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViews();
        initData();
    }

    @Override
    protected void findViews() {
        super.findViews();

        mTitleBar = findViewById(R.id.user_info_title_bar);
        userIcon = findViewById(R.id.user_icon);
        userNick = findViewById(R.id.user_nick_name);
        userSex = findViewById(R.id.user_sex);
        userBirthday = findViewById(R.id.user_birthday);

        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                mFinish();
            }

            @Override
            public void onTitleClick(View v) {
            }

            @Override
            public void onRightClick(View v) {
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        String userSexStr = User.getInstance().getUserSex();
        if ((TextUtils.isEmpty(userSexStr))){
            userSexStr = (String) SPUtils.get(UserInfoActivity.this,Constant.KEY_SEX,"");
        }

        try {
            userNick.setText(User.getInstance().getUserNick());
            userSex.setText(userSexStr);
            userBirthday.setText(User.getInstance().getUserBirthday());
        }catch (Exception e){
        }
    }

    public void onUserIconLayoutClick(View view){
        isSexLayoutPressed = false;
        showDialog(cameraArray);
    }

    public void onUserNickLayoutClick(View view){
        final MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(this).builder()
                .setTitle("请输入昵称")
                .setEditText("");
        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNick.setText(myAlertInputDialog.getResult());
                SPUtils.put(UserInfoActivity.this,Constant.KEY_NICKNAME,myAlertInputDialog.getResult());
                myAlertInputDialog.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    public void onUserSexLayoutClick(View view){
        isSexLayoutPressed = true;
        showDialog(sexArray);
    }

    public void onUserBirthDayLayoutClick(View view){
        showDatePickDialog(DateParams.TYPE_YEAR, DateParams.TYPE_MONTH, DateParams.TYPE_DAY);
    }

    private void showDatePickDialog(@DateParams.Type int... style) {
        Calendar todayCal = Calendar.getInstance();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.add(Calendar.YEAR, -80);

        new DatePickDialog.Builder()
                .setTypes(style)
                .setCurrentDate(todayCal.getTime())
                .setStartDate(startCal.getTime())
                .setEndDate(endCal.getTime())
                .setOnSureListener(new OnSureListener() {
                    @Override
                    public void onSure(Date date) {
                        String message = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        userBirthday.setText(message);
                    }
                })
                .show(this);
    }

    public void showDialog(String[] arr){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(UserInfoActivity.this,R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(UserInfoActivity.this,R.layout.dialog_custom_layout,null);

        final RelativeLayout topLayout = view.findViewById(R.id.top_layout);
        final RelativeLayout bottomLayout = view.findViewById(R.id.bottom_layout);
        final TextView topTextView = view.findViewById(R.id.dialog_custom_top_tv);
        final TextView bottomTV = view.findViewById(R.id.dialog_custom_bottom_tv);
        topTextView.setText(arr[0]);
        bottomTV.setText(arr[1]);
        final String tag = (String) topLayout.getTag();
        final String bottomTag = (String) bottomLayout.getTag();


        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tag) && tag.equalsIgnoreCase(getString(R.string.false_tag))){
                    v.setTag("true");
                    bottomLayout.setTag("false");

                    v.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
                    topTextView.setTextColor(getResources().getColor(R.color.white));

                    bottomLayout.setBackground(getResources().getDrawable(R.color.white));
                    bottomTV.setTextColor(getResources().getColor(R.color.gray));
                }else{
                    v.setTag("false");
                    bottomLayout.setTag("true");

                    v.setBackground(getResources().getDrawable(R.color.white));
                    topTextView.setTextColor(getResources().getColor(R.color.gray));

                    bottomLayout.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
                    bottomTV.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(bottomTag) && bottomTag.equalsIgnoreCase(getString(R.string.false_tag))){
                    v.setTag("false");
                    topLayout.setTag("true");
                    v.setBackground(getResources().getDrawable(R.color.white));
                    bottomTV.setTextColor(getResources().getColor(R.color.gray));

                    topLayout.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
                    topTextView.setTextColor(getResources().getColor(R.color.white));
                }else{
                    topLayout.setTag("false");
                    v.setTag("true");
                    v.setBackground(getResources().getDrawable(R.color.home_glod_text_select));
                    bottomTV.setTextColor(getResources().getColor(R.color.white));

                    topLayout.setBackground(getResources().getDrawable(R.color.white));
                    topTextView.setTextColor(getResources().getColor(R.color.gray));
                }
            }
        });

        Button confirmBtn = view.findViewById(R.id.bottom_dialog_comfirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topTag = (String) topLayout.getTag();
                String bottomTag = (String) bottomLayout.getTag();

                if (!TextUtils.isEmpty(topTag) && !topTag.equalsIgnoreCase(getString(R.string.false_tag))){
                    selectedPosition = 0;
                }else if (!TextUtils.isEmpty(bottomTag) && !bottomTag.equalsIgnoreCase(getString(R.string.false_tag)) ){
                    selectedPosition = 1;
                }else {
                    selectedPosition = 1;
                }

                handleResult();
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dp2px(UserInfoActivity.this, 180));
        dialog.show();
    }

    private void handleResult(){
        if (isSexLayoutPressed){
            userSex.setText(sexArray[selectedPosition]);
            SPUtils.put(UserInfoActivity.this,Constant.KEY_SEX,sexArray[selectedPosition]);
        }else {
            if (selectedPosition == 0){
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_PHOTO);
            }else {
                getPicFromCamera();
            }
        }
    }

    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".png");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        bitmap = ImgUtil.handleImageOnKitKat(this, data);        //ImgUtil是自己实现的一个工具类
                    } else {
                        //4.4以下系统使用这个方法处理图片
                        bitmap = ImgUtil.handleImageBeforeKitKat(this, data);
                    }
                    userIcon.setImageBitmap(bitmap);
                }
                break;
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", tempFile);
                        startPhotoZoom(contentUri);//开始对图片进行裁剪处理
                    } else {
                        startPhotoZoom(Uri.fromFile(tempFile));//开始对图片进行裁剪处理
                    }
                }
                break;
            case CROP_SMALL_PICTURE:  //调用剪裁后返回
                if (data != null) {
                    // 让刚才选择裁剪得到的图片显示在界面上
                    Bitmap photo =BitmapFactory.decodeFile(mFile);
                    userIcon.setImageBitmap(photo);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        File out = new File(getPath());
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //裁剪后的地址
    public  String getPath() {
        //resize image to thumb
        if (mFile == null) {
            mFile = Environment.getExternalStorageDirectory() + "/" +"wode/"+ "outtemp.png";
        }
        return mFile;
    }


}

