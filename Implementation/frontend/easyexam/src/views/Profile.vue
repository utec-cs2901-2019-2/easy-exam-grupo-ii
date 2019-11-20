<template>
    <div class="container m-2">
        
            <div>
            <b-card-group class="float-center">
            <b-card align="center" title="My Profile">

                <b-row>
                    <b-col md="3">
                        <h2>{{user.information.first_name}} <br> {{user.information.last_name}}</h2>
                    </b-col>
                    <b-col md="9">
                        <b-row>
                            <b-col>
                                <b-row>Problem submited</b-row>
                                <b-row>{{user.stats.submitedProb}}</b-row>
                            </b-col>
                            <b-col>
                                <b-row class="text-center">
                                    <b-card-text> Problems selected </b-card-text>
                                </b-row>
                                <b-row class="text center">
                                    <b-card-text> {{user.stats.selectedProb}} </b-card-text>
                                </b-row>
                            </b-col>
                            <b-col>
                                <b-row>Comments</b-row>
                                <b-row>{{user.stats.numberOfComments}}</b-row>
                            </b-col>

                        </b-row>

                    </b-col>
                </b-row>
            </b-card>
            </b-card-group> 
            </div>
        <div class="mt-3">
            <b-card-group deck class="mb-3">
            <b-card border-variant="light" header="Information" class="text-center">
                <b-row>
                    <b-col>
                    <b-card-text>First name:</b-card-text>
                    </b-col>
                    <b-col>
                    <b-card-text>{{user.information.first_name}}</b-card-text>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                    <b-card-text>Last name:</b-card-text>
                    </b-col>
                    <b-col>
                    <b-card-text>{{user.information.last_name}}</b-card-text>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                    <b-card-text>Email:</b-card-text>
                    </b-col>
                    <b-col>
                    <b-card-text>{{user.information.email}}</b-card-text>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                    <b-card-text>Institution: </b-card-text>
                    </b-col>
                    <b-col>
                    <b-card-text>{{user.information.institution}}</b-card-text>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                    <b-card-text>Country: </b-card-text>
                    </b-col>
                    <b-col>
                    <b-card-text>{{user.information.country}}</b-card-text>
                    </b-col>
                </b-row>
                <b-row>
                    <b-col>
                    <b-card-text>Date of Birth: </b-card-text>
                    </b-col>
                    <b-col>
                    <b-card-text>{{user.information.DateOfBirth}}</b-card-text>
                    </b-col>
                </b-row>
                <b-row>
                    <b-button variant="primary" class="float-right"> Edit </b-button>
                </b-row>
                
            </b-card>

            <b-card border-variant="light" header="Exams" align="center">
                <b-list-group>
  <b-list-group-item href="#" active class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">List group item heading</h5>
      <small>3 days ago</small>
    </div>

    <p class="mb-1">
      Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
    </p>

    <small>Donec id elit non mi porta.</small>
  </b-list-group-item>

  <b-list-group-item href="#" class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">List group item heading</h5>
      <small class="text-muted">3 days ago</small>
    </div>

    <p class="mb-1">
      Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
    </p>

    <small class="text-muted">Donec id elit non mi porta.</small>
  </b-list-group-item>

  <b-list-group-item href="#" disabled class="flex-column align-items-start">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Disabled List group item</h5>
      <small class="text-muted">3 days ago</small>
    </div>

    <p class="mb-1">
      Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
    </p>

    <small class="text-muted">Donec id elit non mi porta.</small>
  </b-list-group-item>
</b-list-group>        </b-card>
            </b-card-group>
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
export default {
    created(){
    this.items = JSON.parse(localStorage.getItem('items'))
    this.$store.state.user.username = this.items.map(items => items.username)
    this.$store.state.user.token = this.items.map(items => items.token)
    this.$store.state.user.credits = this.items.map(items => items.credits)
    this.$store.state.user.id = this.items.map(items => items.id)
    this.$store.state.isLogged = true;
    },

    data() {
        return {
            user: {
                stats:{
                    submitedProb: 12,
                    selectedProb: 10,
                    numberOfComments: 1
                },
                information:{
                    first_name: "Anggie",
                    last_name: "Yengle",
                    email: "angie.yengle@gmail.com",
                    institution: "UTEC",
                    country: "Perú",
                    DateOfBirth: "12/01/2019"
                }
            }

        }
    },
    mounted() {
            const tag = axios.get("http://localhost:9898/topics/v1/topics/getTopics");
            tag.then(response => (this.tags = response.data));
    }
}
</script>
