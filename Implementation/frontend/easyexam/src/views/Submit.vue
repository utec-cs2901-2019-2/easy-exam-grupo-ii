<template>
    <div class="container m-4">
        <b-container class="m-2">
            <h1>Submit a Problem!</h1>
            <b-row>
                <b-col class="col-md-6">
                    <b-form-group id="input-group-3" label="What type of problem would you like to submit?" label-for="input-3">
                        <b-form-select
                                id="input-3"
                                v-model="form.food"
                                :options="foods"
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
                                    v-model="form.email"
                                    type="text"
                                    required
                                    placeholder="Please enter your problem title here"
                            ></b-form-input>
                        </b-form-group>

                        <b-form-group id="input-group-2" label="Problem Body" label-for="input-2">
                        </b-form-group>



                        <b-form-group id="input-group-4">
                            <b-form-checkbox-group v-model="form.checked" id="checkboxes-4">
                                <b-form-checkbox value="me">Check me out</b-form-checkbox>
                                <b-form-checkbox value="that">Check that out</b-form-checkbox>
                            </b-form-checkbox-group>
                        </b-form-group>

                        <b-button type="submit" variant="primary" class="m-2">Submit</b-button>
                        <b-button type="reset" variant="danger">Reset</b-button>
                    </b-form>
                </b-col>
                <b-col>
                    <h2>Visualizer!</h2>
                    <b-card class="mt-3" header="Form Data Result">
                    <pre class="m-0">{{ form }}</pre>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    email: '',
                    food: null,
                    checked: []
                },
                foods: [{ text: 'Select One', value: null }, 'Multiple Choice', 'True or False', 'Short Answer', 'Long Answer'],
                show: true
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                alert(JSON.stringify(this.form))
            },
            onReset(evt) {
                evt.preventDefault()
                // Reset our form values
                this.form.email = ''
                this.form.name = ''
                this.form.food = null
                this.form.checked = []
                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            }
        }
    }
</script>