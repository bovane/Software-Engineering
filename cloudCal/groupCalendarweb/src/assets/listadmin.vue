<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-setting"></i> 用户管理</el-breadcrumb-item>
                <el-breadcrumb-item>用户信息列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <el-table :data="data" border style="width: 90%"ref="multipleTable" >
                <el-table-column label="用户名" prop="username"width="180px" ></el-table-column>
                <el-table-column label="用户类别" prop="level" width="120px" ></el-table-column>
                <el-table-column label="中文名" prop="realname"width="150px" ></el-table-column>
                <el-table-column label="手机号" prop="mobile"width="150" ></el-table-column>
                <el-table-column label="状态" prop="status"width="100px" ></el-table-column>
                <el-table-column label="创建时间" prop="createtime" show-overflow-tooltip ></el-table-column>
                <el-table-column label="操作" width="320px" >
                    <template slot-scope="scope" width="100px">
                        <el-button type="text" @click="openedit(scope.row)" >修改密码</el-button>
                        <el-button type="text" @click="changenum(scope.row)" >修改号码</el-button>
                        <el-button type="text" @click="remind(scope.row)" >删除</el-button>
                        <el-button type="text" @click="ban(scope.row)" >禁用/启用</el-button>
                        <el-button type="text" @click="find(scope.row)" >查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-button type="primary" @click="adduser()" align="center">添加用户</el-button>
        </div>
        <el-dialog
            width="30%"
            title="修改密码"
            :visible.sync="dialogFormVisibleed">
            <div>
                <el-form :model="form" :rules="rules" ref="form" label-width="150px">
                    <el-form-item label="新密码" prop="password1">
                        <el-input v-model="form.password1" type="password" placeholder="请输入新密码"></el-input>
                    </el-form-item>
                    <el-form-item label="确认新密码" prop="password2">
                        <el-input v-model="form.password2"type="password" placeholder="请确认新密码"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: center" >
                        <el-button @click="dialogFormVisibleed = false" >取 消</el-button>
                        <el-button type="primary" @click="submit('form')">修改密码</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
        <el-dialog
            width="30%"
            title="添加用户"
            :visible.sync="dialogFormVisibleed1">
            <div class="form-box">
                <el-form :model="form1" :rules="rules1" ref="form1" label-width="150px">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="form1.username" placeholder="请输入用户名"></el-input>
                    </el-form-item>
                        <el-form-item label="密码" prop="password1">
                        <el-input v-model="form1.password1" type="password" placeholder="请输入密码"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="password2">
                        <el-input v-model="form1.password2"type="password" placeholder="请确认密码"></el-input>
                    </el-form-item>
                    <el-form-item label="真实姓名" prop="realname">
                        <el-input v-model="form1.realname" placeholder="请输入真实姓名"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="mobile">
                        <el-input v-model="form1.mobile"placeholder="请输入手机号" :maxlength="11"></el-input>
                    </el-form-item>
                    <el-form-item label="用户级别" prop="level">
                        <el-select v-model="form1.level" placeholder="请选择用户级别">
                            <el-option value="1"label="业务员"></el-option>
                            <el-option value="3"label="财务"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="用户状态" prop="status">
                        <el-select v-model="form1.status" placeholder="请选择用户状态">
                            <el-option value="0"label="可用"></el-option>
                            <el-option value="9"label="禁用"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item style="text-align: center" >
                        <el-button @click="dialogFormVisibleed1 = false" >取 消</el-button>
                        <el-button type="primary" @click="submit1(form1)">添加</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
        <el-dialog
            title="业务员项目详情"
            :visible.sync="dialogVisible"
            style="margin-left:-370px;width:150%;margin-top: -70px">
            <div>
                <el-table :data="data1" border style="width: 100%"ref="multipleTable" >
                    <el-table-column label="项目名称" prop="prjname" show-overflow-tooltip></el-table-column>
                    <el-table-column label="甲方单位名" prop="investor" show-overflow-tooltip></el-table-column>
                    <el-table-column label="联系人" prop="contact"width="100px" ></el-table-column>
                    <el-table-column label="联系方式" prop="contactlink"width="160px" ></el-table-column>
                    <el-table-column label="总金额" prop="totalfee"width="100px" ></el-table-column>
                    <el-table-column label="已收金额" prop="takefee"width="100px"></el-table-column>
                    <el-table-column label="业务员真实姓名" prop="realname"width="150px" ></el-table-column>
                    <el-table-column label="项目状态" prop="prjstate"width="100px" ></el-table-column>
                    <el-table-column label="创建时间" prop="createtime"width="170px" ></el-table-column>
                </el-table>
            </div>
        </el-dialog>
        <el-dialog
            width="30%"
            title="修改手机号码"
            :visible.sync="dialogFormVisibleed2">
            <div>
                <el-form :model="form2" :rules="rules2" ref="form" label-width="150px">
                    <el-form-item label="请输入手机号" prop="mobile">
                        <el-input v-model="form2.mobile" placeholder="请输入新号码"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: center" >
                        <el-button @click="dialogFormVisibleed2 = false" >取 消</el-button>
                        <el-button type="primary" @click="submit2(form2)">修改手机号码</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data: function(){
            var checkpasswd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次确认新密码'));
                } else if (value !== this.form.password1) {
                    callback(new Error('两次输入的新密码不一致!'));
                } else {
                    callback();
                }
            };
            var checkpasswd1 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次确认新密码'));
                } else if (value !== this.form1.password1) {
                    callback(new Error('两次输入的新密码不一致!'));
                } else {
                    callback();
                }
            };
            var checkuser = (rule, value, callback) => {
                this.$http.post("/demo/admin/listuser").then(
                    success=>{
                        for(let i=0;i<success.data.length;i++){
                            if(value == success.data[i].username){
                                callback(new Error('该用户名已存在'));
                            }
                        }
                    }
                )
            }
            return {
                data:[],
                data1:[],
                dialogFormVisibleed:false,
                dialogFormVisibleed1:false,
                dialogFormVisibleed2:false,
                dialogVisible:false,
                form:{
                    username:'',
                    password1:'',
                    password2:''
                },
                rules:{
                    password1:[
                        {required:true,message:'请输入新密码',trigger:'blur'}
                    ],
                    password2:[
                        {required:true,message:'请确认新密码',trigger:'blur'},
                        { validator: checkpasswd, trigger: 'blur' },
                    ],
                },
                form1:{
                    username:'',
                    password1:'',
                    password2:'',
                    level:'',
                    status:'0'
                },
                rules1:{
                    username:[
                        {required:true,message:'请输入用户名',trigger:'blur'},
                        { validator: checkuser, trigger: 'blur'}
                    ],
                    password1:[
                        {required:true,message:'请输入密码',trigger:'blur'}
                    ],
                    password2:[
                        {required:true,message:'请确认密码',trigger:'blur'},
                        { validator: checkpasswd1, trigger: 'blur' },
                    ],
                    level:[
                        {required:true,message:'请选择用户类别',trigger:'blur'}
                    ],
                    realname:[
                        {required:true,message:'请输入中文名',trigger:'blur'}
                    ],
                    mobile:[
                        {required:true,message:'请输入手机号',trigger:'blur'},
                    ],
                    status:[
                        {required:true,message:'请选择用户状态',trigger:'blur'}
                    ]
                },
                form2:{
                    username:'',
                    mobile:''
                },
                rules2:{
                    mobile:[
                        {required:true,message:'请输入手机号',trigger:'blur'},
                    ],
                },
            }
        },
        created(){
            if(localStorage.getItem('ms_username')==""||localStorage.getItem('key')!="0"){
                this.$router.replace('/login')
            }else{
                this.xuanran()
            }
        },
        methods:{
            date(value) {           //将createtime变为普通日期
                let date = new Date(value);
                let   Y = date.getFullYear(),
                    m = date.getMonth() + 1,
                    d = date.getDate(),
                    H = date.getHours(),
                    i = date.getMinutes(),
                    s = date.getSeconds();
                if (m < 10) {
                    m = '0' + m;
                }
                if (d < 10) {
                    d = '0' + d;
                }
                if (H < 10) {
                    H = '0' + H;
                }
                if (i < 10) {
                    i = '0' + i;
                }
                if (s < 10) {
                    s = '0' + s;
                }
                let t = Y+'-'+m+'-'+d+' '+H+':'+i+':'+s;
                return t;
            },
            xuanran(){
                this.$http.post("/demo/admin/listuser").then(
                    success=>{
                        this.data=success.data
                        if(this.data.length>0){
                            for(let i=0;i<this.data.length;i++){
                                this.data[i].createtime=this.date(this.data[i].createtime)
                                if(this.data[i].status==0){
                                    this.data[i].status='可用'
                                }else if(this.data[i].status==9){
                                    this.data[i].status='禁用'
                                }
                                if(this.data[i].level==0){
                                    this.data[i].level='管理员'
                                }else if(this.data[i].level==1){
                                    this.data[i].level='业务员'
                                }else if(this.data[i].level==3){
                                    this.data[i].level='财务'
                                }
                            }
                        }
                    }
                )
            },
            openedit(row){
                this.dialogFormVisibleed=true
                this.form={
                    username: row.username,
                    password1:'',
                    password2:''
                };
            },
            submit(form){
                this.$refs[form].validate((valid)=>
                {
                    if(valid){
                        let params={
                            "username":this.form.username,
                            "password":this.form.password1,
                        }
                        this.$http.post("/demo/users/updatepassword", params,{emulateJSON:true}).then(
                            success=> {
                                this.$message({
                                    type: 'success',
                                    message: '修改成功'
                                });
                                this.form={
                                    username:'',
                                    password1:'',
                                    password2:''
                                };
                                this.dialogFormVisibleed = false;
                            }
                        )
                    }
                });
            },
            ban(row){
                if(row.level=='管理员'){
                    this.$message({
                        type: 'warning',
                        message: '不能禁用管理员用户！'
                    });
                }else{
                    if(row.status=='禁用'){
                        row.status=0
                    }else{
                        row.status=9
                    }
                    let params={
                        "username":row.username,
                        "status":row.status
                    }
                    this.$http.post("/demo/admin/banuser",params,{emulateJSON:true}).then(
                        success=>{
                            this.$message({
                                type:'success',
                                message:'切换成功'
                            })
                            if(row.status=='0'){
                                row.status='可用'
                            }else{
                                row.status='禁用'
                            }
                        }
                    )
                }
            },
            del(row){
                let params={
                    "username":row.username,
                }
                this.$http.post("/demo/admin/deleteuser",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'删除成功'
                        })
                        this.xuanran()
                    }
                )
            },
            find(row){
                if(row.level=='业务员'){
                    this.dialogVisible=true
                }
                let params={
                    "realname":row.realname
                }
                this.$http.post("/demo/prjlisting/myprj", params,{emulateJSON:true}).then(
                    success=>{
                        this.data1=success.data
                        for(let i=0;i<this.data1.length;i++){
                            this.data1[i].createtime = this.date(this.data1[i].createtime)
                            if (this.data1[i].prjstate == 0) {
                                this.data1[i].prjstate = '意向'
                            }else if(this.data1[i].prjstate == 1) {
                                this.data1[i].prjstate = '签约'
                            }else if(this.data1[i].prjstate == 3){
                                this.data1[i].prjstate = '结算'
                            }else if(this.data1[i].prjstate == 5){
                                this.data1[i].prjstate = '完成'
                            }
                        }
                    }
                )
            },
            adduser(){
                this.dialogFormVisibleed1=true
            },
            submit1(form1){
                let params={
                    "username":form1.username,
                    "password":form1.password1,
                    "level":form1.level,
                    "realname":form1.realname,
                    "mobile":form1.mobile,
                    "status":form1.status,
                }
                this.$http.post("/demo/admin/adduser",params).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'添加成功'
                        })
                        this.form1= {
                            username: '',
                            password1: '',
                            password2: '',
                            level: '',
                            realname: '',
                            mobile: '',
                            status: '',
                        }
                        this.xuanran()
                    }
                )
                this.dialogFormVisibleed1=false
            },
            remind(row){
                if(row.level=='管理员'){
                    this.$message({
                        type: 'warning',
                        message: '无法删除管理员用户！'
                    });
                }else{
                    this.$confirm('请确认是否要删除该用户！', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.del(row)
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                    });
                }
            },
            changenum(row){
                this.dialogFormVisibleed2=true;
                this.form={
                    username: row.username,
                    mobile:'',
                };

            },
            submit2(form){
                let params={
                    "username":this.form.username,
                    "mobile":form.mobile,
                }
                this.$http.post("/demo/admin/updatemobile", params,{emulateJSON:true}).then(
                    success=> {
                        this.$message({
                            type: 'success',
                            message: '修改成功'
                        });
                        this.dialogFormVisibleed2 = false;
                    });
                this.xuanran()
            },
        }
    }
</script>

<style scoped>

</style>
