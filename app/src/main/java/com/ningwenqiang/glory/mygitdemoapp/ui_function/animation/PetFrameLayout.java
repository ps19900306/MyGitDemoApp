package com.ningwenqiang.glory.mygitdemoapp.ui_function.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ningwenqiang.glory.mygitdemoapp.R;
import com.ningwenqiang.glory.toollibrary.log.L;


/**
 * 聊天窗口二个宠物
 * create by: 86136
 * create time: 2020/9/2 17:20
 * Function description:
 */
public class PetFrameLayout extends FrameLayout implements View.OnClickListener {


    private boolean isContinue;
    
    private ImageView mLeftLottieAnimationView;
    private ImageView mRightLottieAnimationView;
    private TextView mPetHelloTv;

    private ObjectAnimator flipAnimator1 ;//翻转180
    private ObjectAnimator resetAnimator1 ;//转回来180度

    private ObjectAnimator flipAnimator2 ;//翻转180
    private ObjectAnimator resetAnimator2 ;//转回来180度

    private ValueAnimator goValueAnimator1;
    private ValueAnimator backValueAnimator1;

    private ValueAnimator goValueAnimator2;
    private ValueAnimator backValueAnimator2;

    private ValueAnimator mPetHelloVa2;//这个动画展示过程的第二步
    private AnimatorSet animatorSet1;
    private AnimatorSet animatorSet3;
    private AnimatorSet animatorSet4;
    private AnimatorSet animatorSet5;
    private Animator.AnimatorListener animatorListener;





    private int mNowStep;


    public PetFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public PetFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PetFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNowStep =-1;
        isContinue = false;
        initView();
        initListener();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                Test();
            }
        },1000);
    }

    private void initListener() {
        mLeftLottieAnimationView.setOnClickListener(this);
        mRightLottieAnimationView.setOnClickListener(this);
        animatorListener=new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                nextAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }


    public void Test(){
        initAnimator();
        initAnimatorSet(animatorListener);
        mLeftLottieAnimationView.setBackgroundResource(R.mipmap.group_black_list);
        mRightLottieAnimationView.setBackgroundResource(R.mipmap.group_black_list);
        //这个是开启动画的
        ObjectAnimator flipAnimator =new ObjectAnimator();
        flipAnimator.setDuration(1);
        flipAnimator.setPropertyName("rotationY");
        flipAnimator.setFloatValues(0 , 180);
        flipAnimator.setTarget(mLeftLottieAnimationView);
        flipAnimator.start();
        isContinue=true;
        nextAnimator();
    }

    /**
     * 修改view的位置
     *
     * @param view
     * @param rawX
     */
    private void moveViewByLayout(View view, int rawX) {
        int left = rawX;
        int top = view.getTop();
        int width = left + view.getWidth();
        int height = top + view.getHeight();
        if(view == mLeftLottieAnimationView)
        L.i("left :"+left+"top :"+top+"width :"+width+" height:"+height, "moveViewByLayout", "PetFrameLayout", "nwq", "2020/9/3");
        view.layout(left, top, width, height);
    }

//
//    public void initLottieAnimationView(String leftUserId, String LeftJsonStr, String leftAssetStr, String RightUserId, String RightJsonStr, String RightAssetStr){
//        L.i("", "initLottieAnimationView", "PetFrameLayout", "nwq", "2020/9/3");
//        if(isContinue==true)
//            return;
//        initAnimator();
//        initAnimatorSet(animatorListener);
//        isContinue=true;
//        mLeftLottieAnimationView.setImageAssetsFolder(leftAssetStr);
//        mLeftLottieAnimationView.setAnimation(LeftJsonStr);
//        mLeftLottieAnimationView.setRepeatMode(LottieDrawable.REVERSE);//设置播放模式
//        mLeftLottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);//设置重复次数
//        mLeftLottieAnimationView.playAnimation();
//        mLeftLottieAnimationView.setTag(leftUserId);
//        mLeftLottieAnimationView.setVisibility(VISIBLE);
//
//        mRightLottieAnimationView.setImageAssetsFolder(RightAssetStr);
//        mRightLottieAnimationView.setAnimation(RightJsonStr);
//        mRightLottieAnimationView.setRepeatMode(LottieDrawable.REVERSE);//设置播放模式
//        mRightLottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);//设置重复次数
//        mRightLottieAnimationView.playAnimation();
//        mRightLottieAnimationView.setTag(RightUserId);
//        mRightLottieAnimationView.setVisibility(VISIBLE);
//
//        //这个是开启动画的
//        ObjectAnimator flipAnimator =new ObjectAnimator();
//        flipAnimator.setDuration(1);
//        flipAnimator.setPropertyName("rotationY");
//        flipAnimator.setFloatValues(0 , 180);
//        flipAnimator.setTarget(mLeftLottieAnimationView);
//        flipAnimator.start();
//        nextAnimator();
//    }

    private void nextAnimator(){
        if(!isContinue)
            return;
        mNowStep++;
        switch (mNowStep %5)
        {
            case 0:
                animatorSet1.start();
                break;
            case 1:
                mPetHelloVa2.start();
                break;
            case 2:
                animatorSet3.start();
                break;
            case 3:
                animatorSet4.start();
                break;
            case 4:
                animatorSet5.start();
                break;
        }
    }

    private void initAnimatorSet(Animator.AnimatorListener animatorListener) {

        animatorSet1 =new AnimatorSet();
        animatorSet1.setDuration(goValueAnimator1.getDuration());
        animatorSet1.playTogether(goValueAnimator1,goValueAnimator2);
        animatorSet1.setStartDelay(2000);
        animatorSet1.addListener(animatorListener);

        mPetHelloVa2.addListener(animatorListener);

        animatorSet3=new AnimatorSet();
        animatorSet3.setDuration(resetAnimator1.getDuration());
        animatorSet3.playTogether(resetAnimator1,flipAnimator2);
        animatorSet3.addListener(animatorListener);


        animatorSet4=new AnimatorSet();
        animatorSet4.setDuration(backValueAnimator1.getDuration());
        animatorSet4.playTogether(backValueAnimator1,backValueAnimator2);
        animatorSet4.addListener(animatorListener);

        animatorSet5=new AnimatorSet();
        animatorSet5.setDuration(flipAnimator1.getDuration());
        animatorSet5.playTogether(flipAnimator1,resetAnimator2);
        animatorSet5.addListener(animatorListener);
    }


    private void initAnimator() {
        flipAnimator1 =new ObjectAnimator();
        flipAnimator1.setDuration(100);
        flipAnimator1.setPropertyName("rotationY");
        flipAnimator1.setFloatValues(0 , 180);
        flipAnimator1.setTarget(mLeftLottieAnimationView);

        resetAnimator1 = new ObjectAnimator();
        resetAnimator1.setDuration(100);
        resetAnimator1.setPropertyName("rotationY");
        resetAnimator1.setFloatValues(180 ,0);
        resetAnimator1.setTarget(mLeftLottieAnimationView);

        flipAnimator2 =new ObjectAnimator();
        flipAnimator2.setDuration(100);
        flipAnimator2.setPropertyName("rotationY");
        flipAnimator2.setFloatValues(0 , 180);
        flipAnimator2.setTarget(mRightLottieAnimationView);

        resetAnimator2 = new ObjectAnimator();
        resetAnimator2.setDuration(100);
        resetAnimator2.setPropertyName("rotationY");
        resetAnimator2.setFloatValues(180 ,0);
        resetAnimator2.setTarget(mRightLottieAnimationView);

        int padding=20;
        final int leftMax =(getWidth()/2-mLeftLottieAnimationView.getWidth());

        goValueAnimator1 = ValueAnimator.ofInt(0,leftMax-padding);
        goValueAnimator1.setDuration(3000);
        goValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (Integer) animation.getAnimatedValue();
                moveViewByLayout(mLeftLottieAnimationView, x);
            }
        });
        goValueAnimator1.setInterpolator(new LinearInterpolator());


        backValueAnimator1 = ValueAnimator.ofInt(leftMax-padding,0);
        backValueAnimator1.setDuration(3000);
        backValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (Integer) animation.getAnimatedValue();
                moveViewByLayout(mLeftLottieAnimationView, x);
            }
        });
        backValueAnimator1.setInterpolator(new LinearInterpolator());



        goValueAnimator2 = ValueAnimator.ofInt(getWidth()-mRightLottieAnimationView.getWidth(),getWidth()/2 +padding);
        goValueAnimator2.setDuration(3000);
        goValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (Integer) animation.getAnimatedValue();
                moveViewByLayout(mRightLottieAnimationView, x);
            }
        });
        goValueAnimator2.setInterpolator(new LinearInterpolator());



        backValueAnimator2 = ValueAnimator.ofInt(getWidth()/2 +padding,getWidth()-mRightLottieAnimationView.getWidth());
        backValueAnimator2.setDuration(3000);
        backValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (Integer) animation.getAnimatedValue();
                moveViewByLayout(mRightLottieAnimationView, x);
            }
        });
        backValueAnimator2.setInterpolator(new LinearInterpolator());


        mPetHelloVa2= ValueAnimator.ofInt(1,2);
        mPetHelloVa2.setDuration(2000);
        mPetHelloVa2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (Integer) animation.getAnimatedValue();
                if(x==1)
                {
                    mPetHelloTv.setVisibility(VISIBLE);
                }else {
                    mPetHelloTv.setVisibility(INVISIBLE);
                }
            }
        });

    }


    private void initView() {
        inflate(getContext(), R.layout.widget_pet_frame, this);
        mLeftLottieAnimationView=findViewById(R.id.left_pet_lav);
        mRightLottieAnimationView=findViewById(R.id.right_pet_lav);
        mPetHelloTv=findViewById(R.id.pet_hello_tv);
    }

    @Override
    public void onClick(View v) {
        Object object=v.getTag();
        if(object ==null || !(object instanceof String))
            return;
    }


    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        L.i("", "onDetachedFromWindow", "PetFrameLayout", "nwq", "2020/9/3");
        isContinue=false;
        if(mNowStep !=-1)
        {
            switch (mNowStep %5)
            {
                case 0:animatorSet1.end();
                    break;
                case 1:
                    mPetHelloVa2.end();
                    break;
                case 2:
                    animatorSet3.end();
                    break;
                case 3:
                    animatorSet4.end();
                    break;
                case 4:
                    animatorSet5.end();
                    break;
            }
        }
    }

}
