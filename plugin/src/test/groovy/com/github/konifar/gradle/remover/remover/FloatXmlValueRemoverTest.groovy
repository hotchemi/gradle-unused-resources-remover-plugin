package com.github.konifar.gradle.remover.remover

import spock.lang.Specification

class FloatXmlValueRemoverTest extends Specification {

    def remover = new com.github.konifar.gradle.remover.remover.xmlvaluetype.FloatXmlValueRemover()

    def "type is float"() {
        expect:
        remover.fileType == "float"
    }

    def "resource name is item"() {
        expect:
        remover.resourceName == "dimen"
    }

    def "tag name is item"() {
        expect:
        remover.tagName == "item"
    }

    def "pattern matches"() {
        def pattern = remover.createSearchPattern("size_ratio")
        def isMatched = false
        if (fileText =~ pattern) {
            isMatched = true
        }

        expect:
        isMatched == expected

        where:
        fileText               | expected
        "R.dimen.size_ratio"   | true
        "@dimen/size_ratio\""  | true
        "R.dimen.size"         | false
        "@dimen/size_ratio2\"" | false
        "@style/size_ratio"    | false
    }

}