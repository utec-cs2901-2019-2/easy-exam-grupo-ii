<template>
    <div class="container m-4">
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Problem!</h1>
                <b-row>
                    <b-col class="col-md-6">
                        <b-form-group id="input-group-3" label="What type of problem would you like to submit?" label-for="input-3">
                            <b-form-select
                                    id="input-3"
                                    v-model="problem.type"
                                    :options="type"
                                    required
                            ></b-form-select>
                        </b-form-group>
                        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                            <b-form-group
                                    id="input-group-1"
                                    label="Problem Title:"
                                    label-for="input-1"
                            >
                                <b-form-input
                                        id="input-1"
                                        v-model="problem.title"
                                        type="text"
                                        required
                                        placeholder="Please enter your problem title here"
                                ></b-form-input>
                            </b-form-group>

                            <b-form-group id="input-group-2" label="Problem Body" label-for="input-2">
                                <vue-editor
                                        v-model="problem.body"
                                        placeholder="Please enter your problem body here"
                                        type="text"
                                        required
                                >

                                </vue-editor>
                            </b-form-group>

                            <b-button type="submit" variant="primary" class="m-2">Submit</b-button>
                            <b-button type="reset" variant="danger">Reset</b-button>
                        </b-form>
                    </b-col>
                    <b-col>
                        <h2>Visualizer!</h2>
                        <b-card class="mt-3" header="Form Data Result">
                            <pre class="m-0">{{ problem }}</pre>
                        </b-card>
                    </b-col>
                </b-row>
            </b-container>
        </b-card>

    </div>
</template>

<script>
    import { VueEditor } from "vue2-editor"
    export default {
        data() {
            return {
                problem: {
                    title: '',
                    type: null,
                    body: ''
                },
                type: [{ text: 'Select One', value: null }, 'Multiple Choice', 'True or False', 'Short Answer', 'Long Answer'],
                show: true
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                alert(JSON.stringify(this.problem))
            },
            onReset(evt) {
                evt.preventDefault()
                // Reset our form values
                this.problem.title = ''
                this.problem.type = null
                this.problem.body = ''
                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            }
        },
        components: {
            VueEditor
        }
    }
</script>