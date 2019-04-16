<template>
    <div class="login-wrap">
        <div class="ms-title">用户注册<span style="font-size: 10px; margin-left: 73px;"><u></u></span></div>
        <div class="ms-form">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px">
                <el-form-item prop="username"label="用户名" >
                    <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password"label="密码">
                    <el-input  v-model="ruleForm.password" type="password" placeholder="请输入密码" ></el-input>
                </el-form-item>
                <el-form-item prop="password1"label="确认密码">
                    <el-input  v-model="ruleForm.password1" type="password" placeholder="请确认密码" ></el-input>
                </el-form-item>
                <el-form-item prop="identity"label="身份类型">
                    <el-select v-model="ruleForm.identity" placeholder="请选择身份类型">
                        <el-option value="1"label="学生"></el-option>
                        <el-option value="3"label="教师"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="realname"label="真实姓名">
                    <el-input  v-model="ruleForm.realname"  placeholder="请输入真实姓名" ></el-input>
                </el-form-item>
                <el-form-item prop="sex"label="性别">
                    <el-radio v-model="ruleForm.sex" label="1">男</el-radio>
                    <el-radio v-model="ruleForm.sex" label="2">女</el-radio>
                </el-form-item>
                <el-form-item prop="grade"label="年级">
                    <el-input  v-model="ruleForm.grade" placeholder="请输入年级" ></el-input>
                </el-form-item>
                <el-form-item prop="email"label="邮箱">
                    <el-input  v-model="ruleForm.email" placeholder="请输入邮箱" ></el-input>
                </el-form-item>
                <el-form-item prop="telphone"label="手机号码">
                    <el-input  v-model="ruleForm.telphone" placeholder="请输入手机号码" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="register(ruleForm)" size="large">注册</el-button>
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
                                callback(new Error('该用户名已存在'));
                            }
                        }
                    }
                )
            };
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    password1:'',
                    realname:'',
                    identity:'',
                    sex:'',
                    grade:'',
                    email:'',
                    telphone:'',
                },
                rules:{
                    username:[
                        {required:true,message:'请输入用户名',trigger:'blur'},
                        { validator: checkuser, trigger: 'blur'}
                    ],
                    password:[
                        {required:true,message:'请输入密码',trigger:'blur'}
                    ],
                    password1:[
                        {required:true,message:'请确认密码',trigger:'blur'},
                        { validator: checkpasswd, trigger: 'blur' },
                    ],
                    identity:[
                        {required:true,message:'请选择身份类型',trigger:'blur'}
                    ],
                    realname:[
                        {required:true,message:'请输入中文名',trigger:'blur'}
                    ],
                    sex:[
                        {required:true,message:'请选择性别',trigger:'blur'},
                    ],
                    grade:[
                        {required:true,message:'请输入年级',trigger:'blur'}
                    ],
                    email:[
                        {required:true,message:'请输入邮箱',trigger:'blur'}
                    ],
                    telphone:[
                        {required:true,message:'请输入电话号码',trigger:'blur'}
                    ],
                }
            }
        },
        created(){

        },
        methods: {
            register(ruleForm){
                let params={
                    "username":ruleForm.username,
                    "password":ruleForm.password,
                    "identity":ruleForm.identity,
                    "realname":ruleForm.realname,
                    "sex":ruleForm.sex,
                    "grade":ruleForm.grade,
                    "email":ruleForm.email,
                    "telphone":ruleForm.telphone,
                }
                this.$http.post("/demo/users/adduser",params).then(
                    success=>{
                        this.$message({
                            type: 'success',
                            message: '添加成功'
                        });
                    }
                )
                this.$router.push('/login');
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
