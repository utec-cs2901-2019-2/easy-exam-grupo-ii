<template>
    <div>
        <b-carousel
                id="carousel-1"
                controls
                indicators
                style="border: 1px solid #6c757d"
        >
            <!-- Text slides with image -->
            <b-carousel-slide
                    caption="First slide"
                    text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                    img-src="https://picsum.photos/1024/480/?image=54"
            > </b-carousel-slide>

            <!-- Slides with custom text -->
            <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
                <h1>Hello world!</h1>
            </b-carousel-slide>

            <!-- Slides with image only -->
            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image04.png" width="100%">
                </template>
            </b-carousel-slide>

            <b-carousel-slide>
                <template v-slot:img>
                    <img src="../static/img/image04.png" width="100%">
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