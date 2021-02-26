/**
 * Question.kt
 * Android Assignment2 - Rick and Morty Quiz
 *
 * Created by Anastasiia Egorova on 2021-02-26
 *
 * I certify that this work is solely my own and complies with
 * NBCC Academic Integrity Policy (policy 1111)
 *
 */

package com.example.rickandmortyquiz.game

data class Question(var questionID: Int = 0,
                    var answer: Boolean = false,
                    var attempted: Boolean = false,
                    var answered: Boolean = false) {

}