package com.example.rickandmortyquiz.game

data class Question(var questionID: Int = 0,
                    var answer: Boolean = false,
                    var attempted: Boolean = false,
                    var answered: Boolean = false) {

}