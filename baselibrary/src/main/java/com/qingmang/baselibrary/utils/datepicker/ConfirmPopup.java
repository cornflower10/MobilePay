package com.qingmang.baselibrary.utils.datepicker;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.qingmang.baselibrary.R;
import com.qingmang.baselibrary.utils.SizeUtils;


public abstract class ConfirmPopup<V extends View> extends BottomPopup<View> implements View.OnClickListener {
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private boolean topLineVisible = true;
    private int topLineColor = 0xFFDDDDDD;
    private int topBackgroundColor = Color.WHITE;
    private boolean cancelVisible = true;
    private CharSequence cancelText = "", submitText = "";
    private int cancelTextColor = 0xFF1a9ad4;
    private int submitTextColor = 0xFF1a9ad4;
    private OnConfirmListener onConfirmListener;

    /**
     * Instantiates a new Confirm popup.
     *
     * @param activity the activity
     */
    public ConfirmPopup(Activity activity) {
        super(activity);
        cancelText ="取消";
        submitText ="完成";
    }

    /**
     * Init content view v.
     *
     * @return the v
     */
    protected abstract V initContentView();

    /**
     * Sets top line color.
     *
     * @param topLineColor the top line color
     */
    public void setTopLineColor(@ColorInt int topLineColor) {
        this.topLineColor = topLineColor;
    }

    public void setTopBackgroundColor(@ColorInt int topBackgroundColor) {
        this.topBackgroundColor = topBackgroundColor;
    }

    /**
     * Sets top line visible.
     *
     * @param topLineVisible the top line visible
     */
    public void setTopLineVisible(boolean topLineVisible) {
        this.topLineVisible = topLineVisible;
    }

    /**
     * Sets cancel visible.
     *
     * @param cancelVisible the cancel visible
     */
    public void setCancelVisible(boolean cancelVisible) {
        this.cancelVisible = cancelVisible;
    }

    /**
     * Sets cancel text.
     *
     * @param cancelText the cancel text
     */
    public void setCancelText(CharSequence cancelText) {
        this.cancelText = cancelText;
    }

    /**
     * Sets submit text.
     *
     * @param submitText the submit text
     */
    public void setSubmitText(CharSequence submitText) {
        this.submitText = submitText;
    }

    /**
     * Sets cancel text color.
     *
     * @param cancelTextColor the cancel text color
     */
    public void setCancelTextColor(@ColorInt int cancelTextColor) {
        this.cancelTextColor = cancelTextColor;
    }

    /**
     * Sets submit text color.
     *
     * @param submitTextColor the submit text color
     */
    public void setSubmitTextColor(@ColorInt int submitTextColor) {
        this.submitTextColor = submitTextColor;
    }

    @Override
    protected View getView() {
        LinearLayout rootLayout = new LinearLayout(activity);
//        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(screen.widthPixels/4*3, MATCH_PARENT));
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
//        rootLayout.setBackgroundColor(Color.WHITE);
        rootLayout.setBackgroundResource(R.drawable.list_item_corner);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.setPadding(0, 0, 0, 0);
        rootLayout.setClipToPadding(false);
        LinearLayout topButtonLayout = new LinearLayout(activity);
        topButtonLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, SizeUtils.dp2px(40,activity )));
        topButtonLayout.setBackgroundColor(ContextCompat.getColor(topButtonLayout.getContext(),R.color.page_bg_color));
        topButtonLayout.setGravity(Gravity.CENTER_VERTICAL);
        topButtonLayout.setPadding(SizeUtils.dp2px(16,activity),0,SizeUtils.dp2px(16,activity),0);
        topButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button cancelButton = new Button(activity);
        cancelButton.setVisibility(cancelVisible ? View.VISIBLE : View.GONE);
        cancelButton.setTag(TAG_CANCEL);
        LinearLayout.LayoutParams cancelButtonLayoutParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT,1.0f);
//        cancelButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
//        cancelButtonLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        cancelButtonLayoutParams.gravity = Gravity.LEFT;
        cancelButton.setLayoutParams(cancelButtonLayoutParams);
        cancelButton.setBackgroundColor(Color.TRANSPARENT);
        cancelButton.setGravity(Gravity.LEFT);
        if (!TextUtils.isEmpty(cancelText)) {
            cancelButton.setText(cancelText);
        }
        cancelButton.setTextColor(cancelTextColor);
        cancelButton.setOnClickListener(this);
        topButtonLayout.addView(cancelButton);
        Button submitButton = new Button(activity);
        submitButton.setTag(TAG_SUBMIT);
        LinearLayout.LayoutParams submitButtonLayoutParams = new LinearLayout.LayoutParams(0, WRAP_CONTENT,1.0f);
//        submitButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//        submitButtonLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        cancelButtonLayoutParams.gravity = Gravity.CENTER;
        submitButton.setLayoutParams(submitButtonLayoutParams);
        submitButton.setBackgroundColor(Color.TRANSPARENT);
        submitButton.setGravity(Gravity.RIGHT);
        if (!TextUtils.isEmpty(submitText)) {
            submitButton.setText(submitText);
        }
        submitButton.setTextColor(submitTextColor);
        submitButton.setOnClickListener(this);

//        View lineViewH = new View(activity);
//        LinearLayout.LayoutParams lineHParams = new LinearLayout.LayoutParams(SizeUtils.dp2px(1,lineViewH.getContext()), MATCH_PARENT);
//        lineViewH.setLayoutParams(lineHParams);
//        lineViewH.setBackgroundColor(topLineColor);
//        topButtonLayout.addView(lineViewH);


        topButtonLayout.addView(submitButton);
        rootLayout.addView(topButtonLayout);
        rootLayout.addView(initContentView());

//        if (topLineVisible) {
//            View lineView = new View(activity);
//            lineView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, SizeUtils.dp2px(1,lineView.getContext())));
//            lineView.setBackgroundColor(topLineColor);
//            rootLayout.addView(lineView);
//        }


        return rootLayout;
    }

    @Override
    public void onClick(View v) {
        if (onConfirmListener != null) {
            String tag = v.getTag().toString();
            if (tag.equals(TAG_SUBMIT)) {
                onConfirmListener.onConfirm();
            } else {
                onConfirmListener.onCancel();
            }
        }
        dismiss();
    }

    /**
     * Sets on confirm listener.
     *
     * @param onConfirmListener the on confirm listener
     */
    protected void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }

    /**
     * The type On confirm listener.
     */
    protected static abstract class OnConfirmListener {

        /**
         * On confirm.
         */
        public abstract void onConfirm();

        /**
         * On cancel.
         */
        public void onCancel() {

        }

    }

}
