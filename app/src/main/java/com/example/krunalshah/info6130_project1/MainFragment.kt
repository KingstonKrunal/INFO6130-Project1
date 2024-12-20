package com.example.krunalshah.info6130_project1

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.icu.text.SimpleDateFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@SuppressLint("SimpleDateFormat")
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    private lateinit var wheelImage: ImageView

    var mediaPlayer: MediaPlayer? = null

    private lateinit var dateText: TextView
    private val timer = Timer()

    private lateinit var cloud1ImageView: ImageView
    private lateinit var cloud2ImageView: ImageView
    private lateinit var sunImageView: ImageView
    private lateinit var birdsImageView: ImageView
    private lateinit var seasonImageView: ImageView

    private lateinit var wheelContainer: ConstraintLayout

    var count: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()

        wheelContainer = requireView().findViewById(R.id.wheelContainer)

        mediaPlayer = MediaPlayer.create(context,R.raw.spring_song)
        wheelImage = requireView().findViewById(R.id.wheelImageView)

        dateText = requireView().findViewById(R.id.dateTextView)
        dateText.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                updateTimer()
            }
        }, 0, 1000)

        cloud1ImageView = requireView().findViewById(R.id.cloud1ImageView)
        cloud2ImageView = requireView().findViewById(R.id.cloud2ImageView)
        sunImageView = requireView().findViewById(R.id.sunImageView)
        birdsImageView = requireView().findViewById(R.id.birdsImageView)
        seasonImageView = requireView().findViewById(R.id.seasonImageView)

        val cloud1Animation = ObjectAnimator.ofFloat(cloud1ImageView, "translationX", 300F).apply {
            duration = 2000
            repeatCount = 9999
            repeatMode = ValueAnimator.REVERSE
        }

        val cloud2Animation = ObjectAnimator.ofFloat(cloud2ImageView, "translationX", 350F).apply {
            duration = 2000
            repeatCount = 9999
            repeatMode = ValueAnimator.REVERSE
        }

        val sunAnimation = ObjectAnimator.ofFloat(sunImageView, "translationX", 350F).apply {
            duration = 2500
            repeatCount = 9999
            repeatMode = ValueAnimator.REVERSE
        }

        val birdsAnimation = ObjectAnimator.ofFloat(birdsImageView, "translationX", 1300F).apply {
            duration = 2000
            repeatCount = 9999
            repeatMode = ValueAnimator.RESTART
        }

        startButton = requireView().findViewById(R.id.startButton)
        startButton.setOnClickListener {
            mediaPlayer?.start()

            val wheelRotation = AnimationUtils.loadAnimation(context, R.anim.rotate_speed)
            wheelRotation.setFillAfter(true)
            wheelImage.startAnimation(wheelRotation)

            cloud1Animation.start()
            cloud2Animation.start()
            sunAnimation.start()
            birdsAnimation.start()

//            Handler(Looper.getMainLooper()).postDelayed(Runnable {
//                if (count == 1) {
//                    seasonImageView.animate().apply {
//                        translationX(360f)
//                    }.start()
//                    count++
//                }
//            }, 5000)

//            updateSeason(count)

/*
            val season1 = Runnable{
                cloud1ImageView.animate().apply {
                    rotationXBy(360f)
                }.start()
            }

            val season2 = Runnable{
                //
            }

            val season3 = Runnable{
                //
            }

            val season4 = Runnable{
                //
            }

//            val season1Thread = Thread(season1)
//            season1Thread.start()
//            season1Thread.interrupt()

//            val season2Thread = Thread(season2)
//            season2Thread.start()
//
//            val season3Thread = Thread(season2)
//            season3Thread.start()
//
//            val season4Thread = Thread(season2)
//            season4Thread.start()
*/
        }

        var id = 0
//        val seasonImages = arrayOf(R.drawable.spring, R.drawable.summer, R.drawable.autumn, R.drawable.winter)
//        seasonImageView.setImageResource(seasonImages[id])

        stopButton = requireView().findViewById(R.id.stopButton)
        stopButton.setOnClickListener {
            mediaPlayer?.pause()

            wheelImage.clearAnimation()

            cloud1Animation.end()
            cloud2Animation.end()
            sunAnimation.end()
            birdsAnimation.end()

            val fadeIn: Animation = AlphaAnimation(0F, 1F)
            fadeIn.interpolator = DecelerateInterpolator()
            fadeIn.duration = 1000

            if (id == 0) {
                val color = arrayOf(ColorDrawable(Color.parseColor("#FF4500")), ColorDrawable(Color.parseColor("#8FBC8F")))
                val colorTransition = TransitionDrawable(color)
                wheelContainer.setBackgroundDrawable(colorTransition)
                colorTransition.startTransition(2000)

                val imageAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein)
                seasonImageView.startAnimation(imageAnimation)
                seasonImageView.setImageResource(R.drawable.summer)

                id++
            } else if (id == 1) {
                val color = arrayOf(ColorDrawable(Color.parseColor("#8FBC8F")), ColorDrawable(Color.parseColor("#FFFF00")))
                val colorTransition = TransitionDrawable(color)
                wheelContainer.setBackgroundDrawable(colorTransition)
                colorTransition.startTransition(2000)

                val imageAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein)
                seasonImageView.startAnimation(imageAnimation)
                seasonImageView.setImageResource(R.drawable.autumn)

                id++
            } else if (id == 2) {
                val color = arrayOf(ColorDrawable(Color.parseColor("#FFFF00")), ColorDrawable(Color.parseColor("#FFFFFF")))
                val colorTransition = TransitionDrawable(color)
                wheelContainer.setBackgroundDrawable(colorTransition)
                colorTransition.startTransition(2000)

                val imageAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein)
                seasonImageView.startAnimation(imageAnimation)
                seasonImageView.setImageResource(R.drawable.winter)

                id++
            } else {
                val color = arrayOf(ColorDrawable(Color.parseColor("#FFFFFF")), ColorDrawable(Color.parseColor("#FF4500")))
                val colorTransition = TransitionDrawable(color)
                wheelContainer.setBackgroundDrawable(colorTransition)
                colorTransition.startTransition(2000)

                val imageAnimation = AnimationUtils.loadAnimation(context, R.anim.fadein)
                seasonImageView.startAnimation(imageAnimation)
                seasonImageView.setImageResource(R.drawable.spring)

                id = 0
            }
        }
    }

    private fun updateSeason(count: Int) {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                var c = count

                activity?.runOnUiThread {
                    if (c == 0) {
                        seasonImageView.setImageResource(R.drawable.summer)
                        c++
                    } else if (c == 1) {
                        seasonImageView.setImageResource(R.drawable.autumn)
                        c++
                    } else if (c == 2) {
                        seasonImageView.setImageResource(R.drawable.winter)
                        c++
                    } else {
                        seasonImageView.setImageResource(R.drawable.spring)
                        c = 0
                    }
                }
            }
        }, 0, 15000)
    }

    private fun updateTimer() {
        activity?.runOnUiThread {
            dateText.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}