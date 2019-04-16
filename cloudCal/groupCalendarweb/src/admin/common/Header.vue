<template>
    <div class="header">
        <div class="logo">群日历管理</div>
        <div class="user-info">
            <el-dropdown trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img class="user-logo" src="../../../static/img/img.jpg">
                    {{username}}
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="update">修改个人信息</el-dropdown-item>
                    <el-dropdown-item command="loginout">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
        <el-dialog
            width="30%"
            title="修改信息"
            :visible.sync="dialogFormVisibleed">
            <div class="form-box">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" style="margin-left: 60px">
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
                    <el-form-item align="center">
                        <el-button type="primary" @click="submit(ruleForm)">确认</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                name: 'calendar',
                dialogFormVisibleed:false,
                ruleForm: {
                    username: '',
                    realname:'',
                    sex:'',
                    grade:'',
                    email:'',
                    telphone:'',
                },
                rules:{
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
        computed:{
            username(){
                let username = localStorage.getItem('ms_username');
                return username ? username : this.name;
            }
        },
        methods:{
            handleCommand(command) {
                if(command == 'loginout'){
                    localStorage.removeItem('ms_username')
                    localStorage.removeItem('key')
                    this.$router.push('/login');
                }else if(command == 'update'){
                    this.dialogFormVisibleed=true
                    let params={
                        "username":localStorage.getItem('ms_username')
                    }
                    this.$http.post("/demo/users/list",params,{emulateJSON:true}).then(
                        success=>{
                            this.ruleForm=success.data[0]
                            if(this.ruleForm.sex=='男'){
                                this.ruleForm.sex='1'
                            }else{
                                this.ruleForm.sex='2'
                            }
                        }
                    )
                }
            },
            submit(ruleForm){
                if(ruleForm.sex==1){
                    ruleForm.sex='男'
                }else{
                    ruleForm.sex='女'
                }
                let params={
                    "username":localStorage.getItem('ms_username'),
                    "realname":ruleForm.realname,
                    "sex":ruleForm.sex,
                    "grade":ruleForm.grade,
                    "email":ruleForm.email,
                    "telphone":ruleForm.email,
                }
                console.log(params)
                this.$http.post("/demo/users/updateuser",params).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'修改成功'
                        })
                    }
                )
                this.dialogFormVisibleed=false
            },
        }
    }
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        line-height: 70px;
        color: #fff;
    }
    .header .logo{
        float: left;
        width:250px;
        text-align: center;
    }
    .user-info {
        float: right;
        padding-right: 50px;
        font-size: 16px;
        color: #fff;
    }
    .user-info .el-dropdown-link{
        position: relative;
        display: inline-block;
        padding-left: 50px;
        color: #fff;
        cursor: pointer;
        vertical-align: middle;
    }
    .user-info .user-logo{
        position: absolute;
        left:0;
        top:15px;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>
