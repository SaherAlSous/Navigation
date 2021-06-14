package com.bignerdranch.android.navigation.fragment

import android.app.Activity
import android.icu.math.BigDecimal
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bignerdranch.android.navigation.R
import com.bignerdranch.android.navigation.model.Money

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SpecifyAmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    /**
     * adding Navigation and Data between fragments
     */
    private lateinit var navController: NavController
    private lateinit var recipient : String
    private lateinit var inputamount : TextView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        recipient = arguments?.getString("recipient").toString()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        inputamount = view.findViewById<TextView>(R.id.input_amount)
        val message = "Sending money to $recipient"
        view.findViewById<TextView>(R.id.recipient).text = message

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SpecifyAmountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpecifyAmountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.send_btn -> if (!TextUtils.isEmpty(inputamount.text.toString())){
                val amount = Money(BigDecimal(inputamount.text.toString()))
                val bundle = bundleOf(
                    "recipient" to recipient,
                    "amount" to amount)
                navController.navigate(
                    R.id.action_specifyAmountFragment_to_confirmationFragment,
                bundle)
            }
            R.id.cancel_btn -> activity?.onBackPressed()
        }
    }
}