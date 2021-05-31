package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: CommunicatorFirstToSecond? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as CommunicatorFirstToSecond
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val min: TextView?= view.findViewById(R.id.min_value)
        val max: TextView?= view.findViewById(R.id.max_value)


        generateButton?.setOnClickListener {

            if (min != null && max != null) {
//                if (min.text.isEmpty() || max.text.isEmpty()){
//                   Toast.makeText(requireActivity(), "Please enter number", Toast.LENGTH_SHORT).show()
//                }

                if(min.text.toString().toIntOrNull() == null || max.text.toString().toIntOrNull() == null){
                    Toast.makeText(requireActivity(), "Please enter number \n from 0 to 2147483647", Toast.LENGTH_SHORT).show()
                }

                else if(min.text.toString().toInt() > max.text.toString().toInt()){
                    Toast.makeText(requireActivity(), "Please increase max \n or decrease min ", Toast.LENGTH_SHORT).show()
                }

                else listener?.passData(min.text.toString().toInt(),max.text.toString().toInt())
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
    interface CommunicatorFirstToSecond {
        fun passData(min: Int, max: Int)
    }
}

