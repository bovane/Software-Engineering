<template>
    <div class="login-wrap">
        <div class="ms-title">欢迎登录<span style="font-size: 10px; margin-left: 73px;"><u></u></span></div>
        <div class="demo-input-suffix ms-login">
            <el-form :model="form" :rules="rules" ref="form" label-width="70px" class="demo-form" >
                <el-form-item prop="username"label="用户名" >
                    <el-input v-model="form.username"  prefix-icon="icon-user"  placeholder="username" @keyup.enter.native="submitForm('form')">
                    </el-input>
                </el-form-item>
                <el-form-item prop="password"label="密码" style="margin-bottom: -10px">
                    <el-input  v-model="form.password" type="password" placeholder="password" @keyup.enter.native="submitForm('form')">
                    </el-input>
                    <div style="margin-left: 120px;">
                        <el-button type="text" size="mini" @click="forget" style="color: aliceblue">忘记密码</el-button>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('form')" >登录</el-button>
                    <el-button type="primary" @click="register" >注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
;
<script>
    export default {
        data: function(){
            return {
                form: {
                    username: '',
                    password: '',
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        created(){
            localStorage.setItem('ms_username',"")
            localStorage.setItem('key',"")
            localStorage.setItem('ms_realname',"")
        },
        methods: {
            submitForm(formName) {
                const self = this;
                self.$refs[formName].validate((valid) => {
                    if (valid) {
                        let params={
                            "username":self.form.username,
                        }
                        this.$http.post("/demo/users/login",params).then(
                            success=>{
                                if(success.data){
                                    if(success.data.password==self.form.password){
                                        this.$message({
                                            type:'success',
                                            message:'登入成功'
                                        })
                                        localStorage.setItem('ms_username',self.form.username)
                                        localStorage.setItem('value',success.data.realname)
                                        self.$router.push('/calendar');
                                    }else {
                                        this.$message({
                                            type:'error',
                                            message:'登入失败：密码输入错误'
                                        })
                                    }
                                }else{
                                    this.$message({
                                        type:'error',
                                        message:'登入失败账号输入错误'
                                    })
                                }
                            }
                        )
                    } else {
                        return false;
                    }
                });
            },
            register(){
                this.$router.push('/register');
            },
            forget(){
                this.$router.push('/forget');
            },
        }
    }
</script>

<style scoped>
    .el-radio-button__inner{
        font-size: 8px;
        color: grey;
    }
    .el-input__inner,.el-button{
        border-radius:20px;
    }
    .el-input__inner{
        border-radius:20px;
    }
    .icon-user{

        position: absolute;left: 0;z-index:5;

        background-image: url('../../../static/img/login.jpg'); /*引入图片图片*/

        background-repeat: no-repeat; /*设置图片不重复*/

        background-position: 0px 0px; /*图片显示的位置*/

        width: 20px; /*设置图片显示的宽*/

        height: 20px; /*图片显示的高*/

    }
    .login-wrap{
        position: relative;
        background: url("../../../static/img/login.jpg") no-repeat center;
        background-size:100% 100%;-moz-background-size:100% 100%;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top: 49%;
        left: 0%;
        width: 100%;
        margin-top: -250px;
        text-align: center;
        font-size: 18px;
        color: #fff;
        font-weight: bold;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:36%;
        width:250px;
        height:160px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 20px;
        /* background: #fff;*/
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:50%;
        height:30px;
    }


</style>
