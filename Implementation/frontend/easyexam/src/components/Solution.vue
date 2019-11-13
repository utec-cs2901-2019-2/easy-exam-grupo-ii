<template>
    <div>
        <b-card>
            <b-container class="m-2">
                <h1>Submit a Solution!</h1>
                <b-row>
                    <b-col class="col-md-6">
                        <b-form @submit="onSubmit" @reset="onReset">
                            <b-form-group id="input-group-1" label="What type of problem would you like to submit?" label-for="input-3">
                                <multiselect
                                        v-model="problem.type"
                                        placeholder="Pick a value"
                                        label="name"
                                        track-by="id"
                                        :options="types"
                                        :searchable="false"
                                        :close-on-select="true"
                                        deselectLabel = ""
                                        :allowEmpty = "false"
                                >
                                </multiselect>
                            </b-form-group>
                            <b-form-group id="input-group-2" label="Problem Solution" v-if="problem.type.id > 2" label-for="input-2">
                                <vue-editor
                                        v-model="solution.description"
                                        placeholder="Please enter your solution here"
                                        type="text"
                                        :editor-toolbar="customToolbar"
                                        required
                                >
                                </vue-editor>
                                <b-alert 
                                        :show="dismissCountDownDescrip" 
                                        dismissible 
                                        variant="danger"  
                                        @dismissed="dismissCountDownDescrip=0"
                                        @dismiss-count-down="countDownChanged" 
                                        >
                                        You must need to enter a problem solution.
                                </b-alert>
                               </b-form-group>
                               <b-form-group id="input-group-4" label="Problem Solution" v-if="problem.type.id <= 2" label-for="input-3">
                                <b-input-group class="mt-3">
                                    <b-form-input v-model="solution.alt_body"></b-form-input>
                                    <b-button variant="outline-secondary" @click ="createAlternative">Add alternative</b-button>
                                </b-input-group>
                                <b-table
                                    ref="selectableTable"
                                    selectable
                                    select-mode="multi"
                                    selected-variant="active"
                                    :items="solution.alternatives"
                                    :fields="fields"
                                    @row-selected="onRowSelected"
                                    responsive="sm"
                                >
                                <template v-slot:cell(selected)="{ rowSelected }">
                                    <template v-if="rowSelected">
                                    <span aria-hidden="true">&check;</span>
                                    <span class="sr-only">Selected</span>
                                    </template>
                                    <template v-else>
                                    <span aria-hidden="true">&nbsp;</span>
                                    <span class="sr-only">Not selected</span>
                                    </template>
                                </template>
                            
                                <template v-slot:cell(edit)="row">
                                    <b-button class="float-right" variant="light" @click="deleteAlter(row.index)"><i class="fas fa-trash" style="color:  #e31d1d;"></i></b-button>
                                    <b-button class="float-right" variant="light" ref="btnShow" @click="info(row.item, row.index, $event.target)"><i class="fas fa-edit" style="color: #31a08a;"></i></b-button>                                  
                                </template>
                                </b-table>
                                <b-modal 
                                    title="Update an alternative"
                                    :id="editAlternative.id"
                                    ok-only
                                >
                                <b-form-input v-model="editAlternative.content"> </b-form-input>
                                <template v-slot:modal-footer>
                                    <b-button
                                        variant="primary"
                                        size="sm"
                                        class="float-right"
                                        @click="updateAlter($event.target)"
                                    >
                                    OK
                                    </b-button>
                                    <b-button
                                        variant="danger"
                                        size="sm"
                                        class="float-left"
                                        @click="hideInfo($event.target)"
                                    >
                                    Cancel
                                    </b-button>
                                </template>
                                </b-modal>
                                </b-form-group>
                            
                            <b-form-group id = "input-group-img" label="Select an image (optional)">
                                <b-form-file
                                        v-model="solution.image"
                                        :state="Boolean(solution.image)"
                                        placeholder="Choose an image (.jpeg, .png, .gif) or drop it here..."
                                        drop-placeholder="Drop image here..."
                                        accept="image/jpeg, image/png, image/gif"
                                ></b-form-file>
                            </b-form-group>
                            <b-button variant="success" on-clik class = "m-2 float-left" @click = "goBack" >Back</b-button>
                            <b-button type="submit" variant = "primary" class = "m-2 float-right">Submit</b-button>
                            <b-button type="reset" variant = "danger" class="m-2 float-left" >Reset</b-button>
                        </b-form>
                    </b-col>
                    <b-col>
                        <h2>Visualizador</h2>
                        <b-card class="mt-3" header="Form Data Result">
                            <pre class="m-0">{{ solution }}</pre>
                        </b-card>
                    </b-col>
                </b-row>
            </b-container>
        </b-card>

    </div>
</template>

<script>
    import { VueEditor } from "vue2-editor"
    import { mapState, mapMutations } from 'vuex'
    import axios from "axios"
    import Multiselect from "vue-multiselect"
    import { validationMixin } from 'vuelidate'
    import { minLength, required } from 'vuelidate/lib/validators'
    export default {
        mixins: [validationMixin],
        name: "Solution",
        data() {
            return {
                dismissSecs: 5,
                dismissCountDownDescrip: 0,
                tags: [],
                fields: [
                    {key:'selected', label: 'Value'},
                    {key:'body', label: 'Alternative'},
                    {key:'edit', label: ''}
                ],
                editAlternative: {
                    id: 'edit-alternative',
                    content: '',
                    index: ''
                }
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault();
                let valDescrip = this.solution.description.length > 0 ? true : false;
                if (valDescrip){
                    const p_post = axios.post("http://localhost:3000/problem", {
                        id: new Date(),
                        title: this.problem.title,
                        type: this.problem.type,
                        body: this.problem.body,
                        topics: this.problem.topics_id,
                        rutaImage: this.problem.image,
                        descriptionSolution: this.solution.description,
                        pathImageSolution: this.solution.image
                    });
                    p_post.then(resp => {
                        console.log(resp.data)
                    });
                    p_post.catch(error => {
                        console.log(error)
                    });
                } else{
                    this.showAlertDescription()
                }
            },
            onReset(evt) {
                evt.preventDefault();
                this.solution.description = '';
            },
            goBack () {
                this.$store.commit('updateViewBack')
            },
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            showAlertDescription() {
                this.dismissCountDownDescrip = this.dismissSecs
            },
            info(button, index) {
                this.editAlternative.index = index;
                this.editAlternative.content = this.solution.alternatives[index].body;
                this.$root.$emit('bv::show::modal', this.editAlternative.id , button)
            },
            hideInfo(button){
                this.$root.$emit('bv::hide::modal', this.editAlternative.id, button)
            },
            deleteAlter (index){
                this.solution.updt_id = index;
                this.$store.commit('deleteAlternative')
            },
            ...mapMutations([
                'createAlternative',
                'deleteAlternative',
                'updateAlternative'
            ]),
            onRowSelected(items) {
                console.log(items)
            },
            clearSelected() {
                this.$refs.selectableTable.clearSelected()
            },
            updateAlter(button){
                this.solution.updt_id = this.editAlternative.index;
                this.solution.updt_body = this.editAlternative.content;
                this.$store.commit('updateAlternative')
                this.hideInfo(button);
            }
        },
        mounted() {
            const tag = axios.get("http://localhost:9898/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
        },
        computed: {
            ...mapState ({
                problem: state => state.submit.form.problem,
                solution: state => state.submit.form.solution,
                types: state => state.submit.types,
                customToolbar: state => state.submit.editor
            })
        },
        components: {
            VueEditor,
            Multiselect

        },
        validations: {
            solution: {
                description: {
                    required,
                    minLength: minLength(10)
                }
            }
        }
    }
</script>

<style scoped>

</style>