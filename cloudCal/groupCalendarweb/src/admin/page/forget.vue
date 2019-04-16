<template>
    <div class="login-wrap">
        <div class="ms-title">密码找回<span style="font-size: 10px; margin-left: 73px;"><u></u></span></div>
        <div class="ms-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px">
                <el-form-item prop="username"label="用户名" >
                    <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="email"label="邮箱">
                    <el-input  v-model="ruleForm.email" placeholder="请输入邮箱" ></el-input>
                </el-form-item>
                <el-form-item prop="code"label="验证码">
                    <el-row :gutter="2">
                        <el-col :span="16"><el-input  v-model="ruleForm.code"  placeholder="请输入验证码" ></el-input></el-col>
                        <el-col :span="6"><el-button type="primary" @click="get(ruleForm)">获取验证码</el-button></el-col>
                    </el-row>
                </el-form-item>
                <el-form-item prop="password"label="密码">
                    <el-input  v-model="ruleForm.password" type="password" placeholder="请输入密码" ></el-input>
                </el-form-item>
                <el-form-item align="center">
                    <el-button type="primary" @click="submit(ruleForm)" size="large" >确认</el-button>
                    <el-button type="primary" @click="back()" size="large" >返回</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            var checkpasswd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次确认新密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入的新密码不一致!'));
                } else {
                    callback();
                }
            };
            var checkuser = (rule, value, callback) => {
                this.$http.post("/demo/users/listuser").then(
                    success=>{
                        for(let i=0;i<success.data.length;i++){
                            if(value == success.data[i].username){
                                callback('用户名正确');
                            }
                        }
                        callback(new Error('该用户名不存在'));
                    }
                )
            };
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    email:'',
                    code:'',
                },
                rules:{
                    username:[
                        {required:true,message:'请输入用户名',trigger:'blur'},
                        { validator: checkuser, trigger: 'blur'}
                    ],
                    password:[
                        {required:true,message:'请输入密码',trigger:'blur'}
                    ],
                    email:[
                        {required:true,message:'请确认密码',trigger:'blur'},
                    ],
                    code:[
                        {required:true,message:'请选择身份类型',trigger:'blur'}
                    ],
                }
            }
        },
        created(){

        },
        methods: {
            submit(ruleForm){
                let params={
                    "email":ruleForm.email,
                    "code":ruleForm.code
                }
                this.$http.post("/demo/users/vertifyCode",params,{emulateJSON:true}).then(
                    success=>{
                    }
                )
                let params1={
                    "username":ruleForm.username,
                    "password":ruleForm.password,
                }
                this.$http.post("/demo/users/updatepassword",params1,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type: 'success',
                            message: '修改成功'
                        });
                    }
                )

            },
            get(ruleForm){
                let params={
                    "email":ruleForm.email,
                }
                console.log(params)
                this.$http.post("/demo/users/forgetPassword",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type: 'success',
                            message: '发送成功'
                        });
                    }
                )
            },
            back(){
                this.$router.push('/login');
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
        top: 40%;
        left: 0%;
        width: 100%;
        margin-top: -250px;
        text-align: center;
        font-size: 18px;
        color: #fff;
        font-weight: bold;

    }
    .ms-form{
        position: absolute;
        margin-left: 35%;
        top: 16%;
        width: 25%
    }
    .ms-login{
        position: absolute;
        left:50%;
        top:36%;
        width:300px;
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
