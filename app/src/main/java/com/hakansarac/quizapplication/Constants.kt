package com.hakansarac.quizapplication

object Constants {


    // START
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        var answerList : ArrayList<String> = ArrayList()
        answerList.add("Argentina")
        answerList.add("Australia")
        answerList.add("Armenia")
        answerList.add("Austria")
        val que1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina, answerList, 0
        )

        questionsList.add(que1)
        answerList.clear()
        // 2
        answerList.add("Angola")
        answerList.add("Austria")
        answerList.add("Australia")
        answerList.add("Armenia")
        val que2 = Question(
            2, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            answerList,2
        )

        questionsList.add(que2)
        answerList.clear()
        // 3
        answerList.add("Belarus")
        answerList.add("Belize")
        answerList.add("Brunei")
        answerList.add("Brazil")
        val que3 = Question(
            3, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            answerList, 3
        )

        questionsList.add(que3)
        answerList.clear()
        // 4
        answerList.add("Bahamas")
        answerList.add("Belgium")
        answerList.add("Barbados")
        answerList.add("Belize")
        val que4 = Question(
            4, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            answerList, 1
        )

        questionsList.add(que4)
        answerList.clear()
        // 5
        answerList.add("Gabon")
        answerList.add("France")
        answerList.add("Fiji")
        answerList.add("Finland")
        val que5 = Question(
            5, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            answerList, 2
        )

        questionsList.add(que5)
        answerList.clear()
        // 6
        answerList.add("Germany")
        answerList.add("Georgia")
        answerList.add("Greece")
        answerList.add("none of these")
        val que6 = Question(
            6, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            answerList, 0
        )

        questionsList.add(que6)
        answerList.clear()
        // 7
        answerList.add("Dominica")
        answerList.add("Egypt")
        answerList.add("Denmark")
        answerList.add("Ethiopia")
        val que7 = Question(
            7, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            answerList, 2
        )

        questionsList.add(que7)
        answerList.clear()
        // 8
        answerList.add("Ireland")
        answerList.add("Iran")
        answerList.add("Hungary")
        answerList.add("India")
        val que8 = Question(
            8, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            answerList, 3
        )

        questionsList.add(que8)
        answerList.clear()
        // 9
        answerList.add("Australia")
        answerList.add("New Zealand")
        answerList.add("Tuvalu")
        answerList.add("United States of America")
        val que9 = Question(
            9, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            answerList, 1
        )

        questionsList.add(que9)
        answerList.clear()
        // 10
        answerList.add("Kuwait")
        answerList.add("Jordan")
        answerList.add("Sudan")
        answerList.add("Palestine")
        val que10 = Question(
            10, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            answerList, 0
        )

        questionsList.add(que10)
        answerList.clear()
        return questionsList
    }
    // END
}
// END