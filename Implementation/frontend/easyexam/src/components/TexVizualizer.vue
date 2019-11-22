<template>
    <div v-html="genLatex">
        
    </div>
</template>
<script>
import { parse, HtmlGenerator } from 'latex.js'
import { mapState } from 'vuex'
export default {
    name: 'TexVizualizer',
    computed: {
        ...mapState({
            problem: state => state.submit.form.problem

        }),
        genLatex(){
            let generator = new HtmlGenerator({ hyphenate: false })
            let doc = parse(this.problem.body, { generator: generator }).htmlDocument()
            return doc.documentElement.outerHTML
        }
    }
}
</script>
