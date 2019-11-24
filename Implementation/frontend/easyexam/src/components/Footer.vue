<template>
    <mdb-footer color="stylish-color-dark" class="page-footer font-small pt-4 mt-4">

        <hr />
        <div class="footer-copyright text-center py-3">
            <mdb-container fluid>
                &copy; 2019 EasyExam. Made with &hearts; for you
            </mdb-container>
        </div>
        <hr />

    </mdb-footer>

</template>

<script>
    import { mdbFooter, mdbContainer} from 'mdbvue';
    export default {
        name: 'Footer',
        components: {
            mdbFooter,
            mdbContainer,

        }
    }
</script>

<style scoped>

</style>