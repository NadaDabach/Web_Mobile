package com.nada.miniproject.finalproject

import java.lang.RuntimeException

class Schools {
    private val schools: HashMap<String, School> = HashMap()

    fun addSchool(school: School) {
        schools[school.libelle] = school;
    }

    fun getSchool(libelle: String): School {
        return schools[libelle] ?: throw RuntimeException("Pas d'établissement avec le libellé : $libelle");
    }

    fun getAllSchools(): ArrayList<School> {
        return ArrayList(schools.values.sortedBy { it.libelle })
    }

    fun getTotalNumberOfSchools(): Int {
        return schools.size;
    }

    fun addTofavorite(school: School){
        schools[school.libelle]?.favorite = !schools[school.libelle]?.favorite!!
    }

    fun clean() {
        schools.clear();
    }
}