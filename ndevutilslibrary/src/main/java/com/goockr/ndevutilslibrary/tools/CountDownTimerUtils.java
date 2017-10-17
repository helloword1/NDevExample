package com.goockr.ndevutilslibrary.tools;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * ����ʱ60s
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
		mTextView.setClickable(false); // ���ò��ɵ��
		mTextView.setText(millisUntilFinished / 1000 + "����ط�"); // ���õ���ʱʱ��
		mTextView.setBackgroundResource(bgs); // ���ð�ťΪ��ɫ����ʱ�ǲ��ܵ����

		/**
		 * ������ URLSpan ���ֱ�����ɫ BackgroundColorSpan ������ɫ ForegroundColorSpan �����С
		 * AbsoluteSizeSpan ���塢б�� StyleSpan ɾ���� StrikethroughSpan �»���
		 * UnderlineSpan ͼƬ ImageSpan
		 * http://blog.csdn.net/ah200614435/article/details/7914459
		 */
		SpannableString spannableString = new SpannableString(mTextView
				.getText().toString()); // ��ȡ��ť�ϵ�����
		ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
		/**
		 * public void setSpan(Object what, int start, int end, int flags) {
		 * ��Ҫ��start��end��start����ʼλ��,������Ӣ�ģ�����һ����
		 * ��0��ʼ������end�ǽ���λ�ã����Դ��������֣�������ʼλ�ã�������������λ�á�
		 *
		 * mCountDownTimerUtils = new CountDownTimerUtils(tvGetCode, 60000, 1000);
		 mCountDownTimerUtils.setBackGroupStart(R.drawable.btn_corner_strock);
		 mCountDownTimerUtils.setBackGroupEnd(R.drawable.btn_corner_strock);
		 mCountDownTimerUtils.start();
		 */
		spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);// ������ʱ��ʱ������Ϊ��ɫ
		mTextView.setText(spannableString);
	}

	@Override
	public void onFinish() {
		mTextView.setText("���»�ȡ��֤��");
		mTextView.setClickable(true);// ���»�õ��
		mTextView.setBackgroundResource(bge); // ��ԭ����ɫ
	}

	public void setBackGroupStart(int bgs) {// ��������ɫ
		this.bgs = bgs;
	}

	public void setBackGroupEnd(int bge) {// ��������ʱ��ɫ
		this.bge = bge;
	}
}