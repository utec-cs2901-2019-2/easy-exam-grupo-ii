<template>
    <div>
        <b-carousel
                id="carousel-1"
                controls
                indicators
                style="border: 1px solid #6c757d"
        >
            <!-- Text slides with image -->
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image01.png" width="100%">
                </template>
            </b-carousel-slide>

            <!-- Slides with custom text -->
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image02.jpg" width="100%">
                </template>
            </b-carousel-slide>

            <!-- Slides with image only -->
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image03.png" width="100%">
                </template>
            </b-carousel-slide>

            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image04.png" width="100%">
                </template>
            </b-carousel-slide>
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image05.png" width="100%">
                </template>
            </b-carousel-slide>
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image06.png" width="100%">
                </template>
            </b-carousel-slide>
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image07.png" width="100%">
                </template>
            </b-carousel-slide>
        </b-carousel>
    </div>
</template>

<script>
    export default {
        name: "HomeCarrousel"
    }
</script>

<style scoped>

</style>