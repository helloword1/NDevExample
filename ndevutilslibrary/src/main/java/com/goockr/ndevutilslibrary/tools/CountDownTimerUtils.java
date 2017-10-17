package com.goockr.ndevutilslibrary.tools;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * 倒计时60s
 * 
 * @author ning
 * 
 */
public class CountDownTimerUtils extends CountDownTimer {
	private TextView mTextView;
	private int bgs;
	private int bge;

	/**
	 * @param textView
	 *            The TextView
	 * 
	 * 
	 * @param millisInFuture
	 *            The number of millis in the future from the call to
	 *            {@link #start()} until the countdown is done and
	 *            {@link #onFinish()} is called.
	 * @param countDownInterval
	 *            The interval along the way to receiver {@link #onTick(long)}
	 *            callbacks.
	 */
	public CountDownTimerUtils(TextView textView, long millisInFuture,
                               long countDownInterval) {
		super(millisInFuture, countDownInterval);
		this.mTextView = textView;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		mTextView.setClickable(false); // 设置不可点击
		mTextView.setText(millisUntilFinished / 1000 + "秒后重发"); // 设置倒计时时间
		mTextView.setBackgroundResource(bgs); // 设置按钮为灰色，这时是不能点击的

		/**
		 * 超链接 URLSpan 文字背景颜色 BackgroundColorSpan 文字颜色 ForegroundColorSpan 字体大小
		 * AbsoluteSizeSpan 粗体、斜体 StyleSpan 删除线 StrikethroughSpan 下划线
		 * UnderlineSpan 图片 ImageSpan
		 * http://blog.csdn.net/ah200614435/article/details/7914459
		 */
		SpannableString spannableString = new SpannableString(mTextView
				.getText().toString()); // 获取按钮上的文字
		ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
		/**
		 * public void setSpan(Object what, int start, int end, int flags) {
		 * 主要是start跟end，start是起始位置,无论中英文，都算一个。
		 * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
		 *
		 * mCountDownTimerUtils = new CountDownTimerUtils(tvGetCode, 60000, 1000);
		 mCountDownTimerUtils.setBackGroupStart(R.drawable.btn_corner_strock);
		 mCountDownTimerUtils.setBackGroupEnd(R.drawable.btn_corner_strock);
		 mCountDownTimerUtils.start();
		 */
		spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);// 将倒计时的时间设置为红色
		mTextView.setText(spannableString);
	}

	@Override
	public void onFinish() {
		mTextView.setText("重新获取验证码");
		mTextView.setClickable(true);// 重新获得点击
		mTextView.setBackgroundResource(bge); // 还原背景色
	}

	public void setBackGroupStart(int bgs) {// 倒数是颜色
		this.bgs = bgs;
	}

	public void setBackGroupEnd(int bge) {// 倒数结束时颜色
		this.bge = bge;
	}
}
