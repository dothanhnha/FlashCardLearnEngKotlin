package com.example.flashcardlearneng.model

class Category {
    var nameCategory: String = ""
    var percentLearned: Int = 0
    var wordLearned: Int
        get
        set(value) {
            this.percentLearned = ((value.toFloat() / this.wordTotal.toFloat())*100f).toInt()
            field = value
        }

    var wordTotal: Int
        get
        set(value) {
            this.percentLearned = ((this.wordLearned.toFloat() / value.toFloat())*100f).toInt()
            field = value
        }

    constructor(nameCategory: String, wordLearned: Int, wordTotal: Int) {
        this.nameCategory = nameCategory
        this.percentLearned = percentLearned
        this.wordTotal = wordTotal
        this.wordLearned = wordLearned
        this.percentLearned = ((this.wordLearned.toFloat() / this.wordTotal.toFloat())*100f).toInt()
    }
}