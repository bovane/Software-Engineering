<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-setting"></i> 事件管理</el-breadcrumb-item>
                <el-breadcrumb-item>用户事件列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <vue-event-calendar
            :events="demoEvents"
            @day-changed="handleDayChanged"
            @month-changed="handleMonthChanged"
        ></vue-event-calendar>
    </div>
</template>

<script>
    let today = new Date()
    export default {
        name: 'app',
        data () {
            return {
                demoEvents: [{
                    date: `${today.getFullYear()}/${today.getMonth() + 1}/15`,
                    title: 'Title-1',
                    desc: 'longlonglong description'
                },{
                    date: `${today.getFullYear()}/${today.getMonth() + 1}/24`,
                    title: 'Title-2'
                },{
                    date: `${today.getFullYear()}/${today.getMonth() === 11 ? 1 : today.getMonth() + 1}/06`,
                    title: 'Title-3',
                    desc: 'description'
                }]
            }
        },
        created(){
            if(localStorage.getItem('ms_username')==""){
                this.$router.replace('/login')
            }
        },
        methods: {
            handleDayChanged (data) {
                //console.log('date-changed', data)
            },
            handleMonthChanged (data) {
                //console.log('month-changed', data)
            }
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        color: #2c3e50;
        margin-top: 0px;
    }

    h1, h2, h3 {
        font-weight: normal;
        margin: 0;
        padding: 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

/*    li {
        display: inline-block;
        margin: 0 10px;
    }*/
    a {
        color: #42b983;
    }
    .t-center{
        text-align: center;
        margin: 20px;
    }
    .mt150{
        margin-top: 150px;
    }
</style>
