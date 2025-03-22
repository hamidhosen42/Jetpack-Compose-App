package com.hamidhosen.listdemoapp

data class MarvelChar (
    var charName :String,
    var name:String,
    var imageRes:Int
)

fun  getAllMarvelChars():List<MarvelChar>{
    return listOf<MarvelChar>(
        MarvelChar("Hamid","Hosen",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
        MarvelChar("Hamid1","Hosen1",R.drawable.profile),
    )
}