<template>
    <div class="container">
        <br>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <header class="card-header">
                        <a href="/login" class="float-right btn btn-outline-primary mt-1">Log in</a>
                        <h4 class="card-title mt-2">Sign up</h4>
                    </header>
                    <article class="card-body">
                        <b-alert v-model="emailCheck" variant="danger" dismissible>
                            User already register
                        </b-alert>
                        <b-form @submit.prevent="loadpost">
                            <label>Name <font color="red">*</font></label>
                            <div class="form-row">
                                <div class="col form-group">
                                    <input v-model="firstName" type="text" class="form-control" placeholder="First Name" :class="{ 'is-invalid': submitted && !firstName }">
                                    <div v-show="submitted && !firstName" class="invalid-feedback">First Name is required</div>
                                </div>

                                <div class="col form-group">
                                    <input v-model="lastName" type="text" class="form-control" placeholder="Last Name" :class="{ 'is-invalid': submitted && !lastName }">
                                    <div v-show="submitted && !lastName" class="invalid-feedback">Last Name is required</div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Email address <font color="red">*</font></label>
                                <input @keyup="emailCheck = false" v-model="email" type="email" class="form-control" placeholder="University / job email address, e.g.name@utec.edu" :class="{ 'is-invalid': submitted && !email }">
                                <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                                <div v-show="submitted && !email" class="invalid-feedback">Email is required</div>
                            </div>


                            <div class="form-row">

                                <div class = "form-group col-md-6">
                                    <label> Institution <font color="red">*</font></label>
                                    <input v-model="institution" type="text" class = "form-control" placeholder="University / job name" :class="{ 'is-invalid': submitted && !institution }">
                                    <div v-show="submitted && !institution" class="invalid-feedback">Institution is required</div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label>Country / region <font color="red">*</font></label>
                                     <b-form-select
                                    v-model="country"
                                    :options="countries"
                                    required
                                    ></b-form-select>
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class = "form-group col-md-6">
                                    <label>Date of Birth <font color="red">*</font></label>
                                    <b-form-input @keyup="birthdateValidate = true" v-model = 'birthdate' type="date" :class="{ 'is-invalid': submitted && !birthdate }"></b-form-input>
                                    <div v-show="submitted && !birthdate && !birthdateValidate" class="invalid-feedback">Date of birth is required</div>
                                    <div v-show="birthdateValidate && submitted" class="invalid-feedback">Invalid date</div>
                                </div>
                                
                                <div class="form-group col-md-6">
                                    <label>Gender <font color="red">*</font></label>
                                     <b-form-select
                                    v-model="gender"
                                    :options="genders"
                                    required
                                    ></b-form-select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input @keyup="checkLenght" v-model="password" class="form-control" type="password" placeholder="New Password" :class="{ 'is-invalid': submitted && !password || passLenght}">
                                <small class="form-text text-muted">Make sure it al least 6 characters.</small>
                                <div v-show="submitted && !password || passLenght" class="invalid-feedback">Full password is required</div>
                            </div>

                            <div class="form-group">
                                <label>Confirm Password <font color="red">*</font></label>
                                <input @keyup ="validatePassword" v-model="confirm_password" class="form-control" type="password" placeholder="Retype your password" :class="{ 'is-invalid': submitted && !confirm_password || passwordCheck}">
                                <div v-show="submitted && !confirm_password || passwordCheck" class="invalid-feedback">Password not match</div>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block"> Register  </button>
                            </div>

                            <small class="text-muted">By clicking the 'Sign Up' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>

                        </b-form>
                    </article>

                    <div class="border-top card-body text-center">Have an account? <a href="/login">Log In</a></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'register',
  components: {
  },
  data(){
      return{
          submitted: false,
          firstName: '',
          lastName: '',
          email: '',
          password: '',
          confirm_password: '',
          institution: '',
          gender:'',
          birthdate: '',
          country: '',
          passwordCheck: false,
          passLenght:false,
          emailCheck: false,
          birthdateValidate: false,
          genders: [ "Female", "Male", "Other"],
          countries: [
            "Afghanistan",
            "Åland Islands",
            "Albania",
            "Algeria",
            "American Samoa",
            "Andorra",
            "Angola",
            "Anguilla",
            "Antarctica",
            "Antigua and Barbuda",
            "Argentina",
            "Armenia",
            "Aruba",
            "Australia",
            "Austria",
            "Azerbaijan",
            "Bahamas",
            "Bahrain",
            "Bangladesh",
            "Barbados",
            "Belarus",
            "Belgium",
            "Belize",
            "Benin",
            "Bermuda",
            "Bhutan",
            "Bolivia, Plurinational State of",
            "Bonaire, Sint Eustatius and Saba",
            "Bosnia and Herzegovina",
            "Botswana",
            "Bouvet Island",
            "Brazil",
            "British Indian Ocean Territory",
            "Brunei Darussalam",
            "Bulgaria",
            "Burkina Faso",
            "Burundi",
            "Cambodia",
            "Cameroon",
            "Canada",
            "Cape Verde",
            "Cayman Islands",
            "Central African Republic",
            "Chad",
            "Chile",
            "China",
            "Christmas Island",
            "Cocos (Keeling) Islands",
            "Colombia",
            "Comoros",
            "Congo",
            "Congo, the Democratic Republic of the",
            "Cook Islands",
            "Costa Rica",
            "Côte d'Ivoire",
            "Croatia",
            "Cuba",
            "Curaçao",
            "Cyprus",
            "Czech Republic",
            "Denmark",
            "Djibouti",
            "Dominica",
            "Dominican Republic",
            "Ecuador",
            "Egypt",
            "El Salvador",
            "Equatorial Guinea",
            "Eritrea",
            "Estonia",
            "Ethiopia",
            "Falkland Islands (Malvinas)",
            "Faroe Islands",
            "Fiji",
            "Finland",
            "France",
            "French Guiana",
            "French Polynesia",
            "French Southern Territories",
            "Gabon",
            "Gambia",
            "Georgia",
            "Germany",
            "Ghana",
            "Gibraltar",
            "Greece",
            "Greenland",
            "Grenada",
            "Guadeloupe",
            "Guam",
            "Guatemala",
            "Guernsey",
            "Guinea",
            "Guinea-Bissau",
            "Guyana",
            "Haiti",
            "Heard Island and McDonald Islands",
            "Holy See (Vatican City State)",
            "Honduras",
            "Hong Kong",
            "Hungary",
            "Iceland",
            "India",
            "Indonesia",
            "Iran, Islamic Republic of",
            "Iraq",
            "Ireland",
            "Isle of Man",
            "Israel",
            "Italy",
            "Jamaica",
            "Japan",
            "Jersey",
            "Jordan",
            "Kazakhstan",
            "Kenya",
            "Kiribati",
            "Korea, Democratic People's Republic of",
            "Korea, Republic of",
            "Kuwait",
            "Kyrgyzstan",
            "Lao People's Democratic Republic",
            "Latvia",
            "Lebanon",
            "Lesotho",
            "Liberia",
            "Libya",
            "Liechtenstein",
            "Lithuania",
            "Luxembourg",
            "Macao",
            "Macedonia, the Former Yugoslav Republic of",
            "Madagascar",
            "Malawi",
            "Malaysia",
            "Maldives",
            "Mali",
            "Malta",
            "Marshall Islands",
            "Martinique",
            "Mauritania",
            "Mauritius",
            "Mayotte",
            "Mexico",
            "Micronesia, Federated States of",
            "Moldova, Republic of",
            "Monaco",
            "Mongolia",
            "Montenegro",
            "Montserrat",
            "Morocco",
            "Mozambique",
            "Myanmar",
            "Namibia",
            "Nauru",
            "Nepal",
            "Netherlands",
            "New Caledonia",
            "New Zealand",
            "Nicaragua",
            "Niger",
            "Nigeria",
            "Niue",
            "Norfolk Island",
            "Northern Mariana Islands",
            "Norway",
            "Oman",
            "Pakistan",
            "Palau",
            "Palestine, State of",
            "Panama",
            "Papua New Guinea",
            "Paraguay",
            "Peru",
            "Philippines",
            "Pitcairn",
            "Poland",
            "Portugal",
            "Puerto Rico",
            "Qatar",
            "Réunion",
            "Romania",
            "Russian Federation",
            "Rwanda",
            "Saint Barthélemy",
            "Saint Helena, Ascension and Tristan da Cunha",
            "Saint Kitts and Nevis",
            "Saint Lucia",
            "Saint Martin (French part)",
            "Saint Pierre and Miquelon",
            "Saint Vincent and the Grenadines",
            "Samoa",
            "San Marino",
            "Sao Tome and Principe",
            "Saudi Arabia",
            "Senegal",
            "Serbia",
            "Seychelles",
            "Sierra Leone",
            "Singapore",
            "Sint Maarten (Dutch part)",
            "Slovakia",
            "Slovenia",
            "Solomon Islands",
            "Somalia",
            "South Africa",
            "South Georgia and the South Sandwich Islands",
            "South Sudan",
            "Spain",
            "Sri Lanka",
            "Sudan",
            "Suriname",
            "Svalbard and Jan Mayen",
            "Swaziland",
            "Sweden",
            "Switzerland",
            "Syrian Arab Republic",
            "Taiwan, Province of China",
            "Tajikistan",
            "Tanzania, United Republic of",
            "Thailand",
            "Timor-Leste",
            "Togo",
            "Tokelau",
            "Tonga",
            "Trinidad and Tobago",
            "Tunisia",
            "Turkey",
            "Turkmenistan",
            "Turks and Caicos Islands",
            "Tuvalu",
            "Uganda",
            "Ukraine",
            "United Arab Emirates",
            "United Kingdom",
            "United States",
            "United States Minor Outlying Islands",
            "Uruguay",
            "Uzbekistan",
            "Vanuatu",
            "Venezuela, Bolivarian Republic of",
            "Viet Nam",
            "Virgin Islands, British",
            "Virgin Islands, U.S.",
            "Wallis and Futuna",
            "Western Sahara",
            "Yemen",
            "Zambia",
            "Zimbabwe"]
      }
  },
  computed:{
  },
    methods:{
        loadpost () {
            this.submitted = true;
            
            var max = new Date(1930,0,1)
            var min = new Date(2002,0,1)
            var birth = new Date(this.birthdate)
            
            if (birth < min && birth > max) {
                this.birthdateValidate =  false
            } else {
                this.birthdateValidate =  true
                this.birthdate = ''
            }

            if (this.firstName && this.lastName && 
                this.email && this.password && this.confirm_password &&
                this.password == this.confirm_password && !this.birthdateValidate &&
                this.institution && this.gender && this.birthdate && this.country) {

                    axios.post('http://'+ this.$store.state.clientURL + '/api/v1/register', {
                        firstname:this.firstName,
                        lastname: this.lastName,
                        country: this.country,
                        user : {
                            email: this.email,
                            password: this.password,
                            role: {
                                id:1,
                                name:''
                            }
                        },
                        institution: this.institution,
                        gender: this.gender,
                        date_birth: this.birthdate
                    })
                        .then((response) => {
                            if (response.data.message == "success") {
                                //console.log(response.data.result)
                                //this.$store.state.user.username = response.data.result.username
                                //this.$store.state.user.token = response.data.result.token
                                //this.$store.state.user.credits = response.data.result.credits
                                this.$router.push('/login');
                            } else {
                                this.emailCheck = true;
                                this.email = '';
                            }
                        }, (error) => {
                            console.log(error);
                        })
                }
        },
        validatePassword () {
            if (this.password != this.confirm_password) {
                this.passwordCheck =  true;
            } else {
                this.passwordCheck =  false;
            }
        },
        checkLenght(){
            if (this.password.length<6){
                this.passLenght= true;
            } else {
                this.passLenght =  false;
            }
        }
    },
}
</script>
