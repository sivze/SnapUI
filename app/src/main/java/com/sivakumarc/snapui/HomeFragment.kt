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
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        icon_home.setOnClickListener {
            executeTransition(it, R.id.home_state)
        }

        icon_saved.setOnClickListener {
            executeTransition(it, R.id.left_state)
        }

        icon_discover.setOnClickListener {
            executeTransition(it, R.id.right_state)
        }

        saved_materialTextView.setOnClickListener {
            executeTransition(icon_saved, R.id.left_state)
        }

        discover_materialTextView.setOnClickListener {
            executeTransition(icon_discover, R.id.right_state)
        }
    }

    private fun executeTransition(view: View?, toState: Int) {
        val currentState = home_motion_layout.currentState

        onViewClicked(view)

        if(toState == currentState) {
            return
        }

        if(toState == R.id.home_state && currentState != R.id.home_state) {
            icon_home.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }

        home_motion_layout.setTransition(currentState, toState)
        home_motion_layout.setTransitionDuration(200)
        home_motion_layout.transitionToEnd()
    }

    private fun onViewClicked(view: View?) {
        view?: return
        view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.view_clicked))
    }
}
