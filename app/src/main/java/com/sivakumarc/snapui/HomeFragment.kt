/*
 *
 *  *    Copyright (C) 2019 Sivakumar Chellamuthu
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.sivakumarc.snapui

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sivakumarc.snapui.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.home_fragment,
                container,
                false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.handler = object : HomeHandler {
            override fun onIconHomeClick(v: View) {
                executeTransition(v, R.id.home_state)
            }

            override fun onIconSavedClick(v: View) {
                executeTransition(v, R.id.left_state)
            }

            override fun onIconDiscoverClick(v: View) {
                executeTransition(v, R.id.right_state)
            }

            override fun onTextSavedClick(v: View) {
                executeTransition(binding.iconSaved, R.id.left_state)
            }

            override fun onTextDiscoverClick(v: View) {
                executeTransition(binding.iconDiscover, R.id.right_state)
            }

        }
    }

    private fun executeTransition(view: View?, toState: Int) {
        val currentState = home_motion_layout.currentState

        onViewClicked(view)

        if (toState == currentState) {
            return
        }

        if (toState == R.id.home_state && currentState != R.id.home_state) {
            binding.iconHome.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }

        binding.homeMotionLayout.setTransition(currentState, toState)
        binding.homeMotionLayout.setTransitionDuration(200)
        binding.homeMotionLayout.transitionToEnd()
    }

    private fun onViewClicked(view: View?) {
        view ?: return
        view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.view_clicked))
    }
}
