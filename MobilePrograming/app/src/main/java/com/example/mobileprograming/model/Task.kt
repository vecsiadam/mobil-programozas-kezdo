package com.example.mobileprograming.model

class Task {

    var id:Int=0
    var name:String?=null
    var desc:String?=null
    var date:String?=null

    constructor(){}

    constructor( name:String, desc:String, date:String){
        this.name=name
        this.desc=desc
        this.date=date
    }

    constructor( id:Int, name:String, desc:String, date:String){
        this.id = id
        this.name=name
        this.desc=desc
        this.date=date
    }

}