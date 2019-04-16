<template>
    <div style="margin-top: 5%;margin-left: 5%">
        <el-table :data="data" border style="width: 90%"ref="multipleTable">
            <el-table-column label="活动名称" sortable prop="topic" show-overflow-tooltip></el-table-column>
            <el-table-column label="开始时间" prop="begintime" show-overflow-tooltip ></el-table-column>
            <el-table-column label="结束时间" prop="endtime"show-overflow-tooltip></el-table-column>
            <el-table-column label="活动地方" prop="location" width="100px" ></el-table-column>
            <el-table-column label="备注" sortable prop="notes"width="120px" ></el-table-column>
            <el-table-column label="操作"show-overflow-tooltip width="260px" >
                <template slot-scope="scope" width="80px">
                    <el-button type="text" @click="update(scope.row)">修改</el-button>
                    <el-button type="text" @click="remind(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="add" align="center">添加事件</el-button>
        <el-dialog
            width="30%"
            title="添加事件"
            :visible.sync="dialogFormVisibleed1">
            <div class="form-box">
                <el-form :model="form1" :rules="rules1" ref="form1" label-width="150px">
                    <el-form-item label="标题" prop="topic">
                        <el-input v-model="form1.topic" placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item label="起始时间" prop="begintime">
                        <el-date-picker v-model="form1.begintime" type="date" placeholder="请选择起始时间"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="终止时间" prop="endtime">
                        <el-date-picker v-model="form1.endtime" type="date" placeholder="请选择终止时间"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="循环方式" prop="cycmode">
                        <el-select v-model="form1.cycmode" placeholder="请选择循环方式">
                            <el-option value="0"label="无"></el-option>
                            <el-option value="1"label="按日循环"></el-option>
                            <el-option value="3"label="按周循环"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="活动地点" prop="location">
                        <el-input v-model="form1.location"placeholder="请输入活动地点"></el-input>
                    </el-form-item>
                    <el-form-item label="提醒时间" prop="remindtime">
                        <el-date-picker v-model="form1.remindtime" type="date" placeholder="请选择提醒时间"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="提醒方式" prop="remindway">
                        <el-select v-model="form1.remindway" placeholder="请选择提醒方式">
                            <el-option value="0"label="短信"></el-option>
                            <el-option value="1"label="邮箱"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注" prop="notes">
                        <el-input v-model="form1.notes"placeholder="请输入备注"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: center" >
                        <el-button type="primary" @click="submit1(form1)">添加</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
        <el-dialog
            width="30%"
            title="修改事件"
            :visible.sync="dialogFormVisibleed2">
            <div class="form-box">
                <el-form :model="form2" :rules="rules2" ref="form2" label-width="150px">
                    <el-form-item label="标题" prop="topic">
                        <el-input v-model="form2.topic" placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item label="起始时间" prop="begintime">
                        <el-date-picker v-model="form2.begintime" type="date" placeholder="请选择起始时间"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="终止时间" prop="endtime">
                        <el-date-picker v-model="form2.endtime" type="date" placeholder="请选择终止时间"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="循环方式" prop="cycmode">
                        <el-select v-model="form2.cycmode" placeholder="请选择循环方式">
                            <el-option value="0"label="无"></el-option>
                            <el-option value="1"label="按日循环"></el-option>
                            <el-option value="3"label="按周循环"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="活动地点" prop="location">
                        <el-input v-model="form2.location"placeholder="请输入活动地点"></el-input>
                    </el-form-item>
                    <el-form-item label="提醒时间" prop="remindtime">
                        <el-date-picker v-model="form2.remindtime" type="date" placeholder="请选择提醒时间"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="提醒方式" prop="remindway">
                        <el-select v-model="form2.remindway" placeholder="请选择提醒方式">
                            <el-option value="0"label="短信"></el-option>
                            <el-option value="1"label="邮箱"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注" prop="notes">
                        <el-input v-model="form2.notes"placeholder="请输入备注"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: center" >
                        <el-button type="primary" @click="submit2(form2)">修改</el-button>
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
                form1:{
                    topic:'',
                    begintime:'',
                    endtime:'',
                    cycmode:'',
                    location:'',
                    remindtime:'',
                    remindway:'',
                    notes:'',
                },
                rules1:{},
                form2:{
                    topic:'',
                    begintime:'',
                    endtime:'',
                    cycmode:'',
                    location:'',
                    remindtime:'',
                    remindway:'',
                    notes:'',
                },
                rules2:{},
                dialogFormVisibleed1:false,
                dialogFormVisibleed2:false,
            }
        },
        created(){
            if(localStorage.getItem('ms_username')==""){
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
                let params={
                    "username":localStorage.getItem('ms_username')
                }
                this.$http.post("/demo/calendar/list",params,{emulateJSON:true}).then(
                    success=>{
                        this.data=success.data
                        if(this.data.length>0){
                            for(let i=0;i<this.data.length;i++){
                                this.data[i].begintime=this.date(this.data[i].begintime)
                                this.data[i].endtime=this.date(this.data[i].endtime)
                                this.data[i].remindtime=this.date(this.data[i].remindtime)
                            }
                        }
                    }
                )
            },
            update(row){
                this.dialogFormVisibleed2=true
                this.form2=row
            },
            remind(row){
                this.$confirm('请确认是否要删除该事件！', '提示', {
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
            },
            del(row){
                let params={
                    "eventid":row.eventid,
                }
                console.log(params)
                this.$http.post("/demo/calendar/delete",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'删除成功'
                        })
                        this.xuanran()
                    }
                )
            },
            submit1(form1){
                let params={
                    "username":localStorage.getItem('ms_username'),
                    "topic":form1.topic,
                    "begintime":form1.begintime,
                    "endtime":form1.endtime,
                    "cycmode":form1.cycmode,
                    "location":form1.location,
                    "remindtime":form1.remindtime,
                    "remindway":form1.remindway,
                    "notes":form1.notes,
                }
                console.log(params)
                this.$http.post("/demo/calendar/add",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'添加成功'
                        })
                        this.dialogFormVisibleed1=false
                        this.xuanran()
                    }
                )
            },
            submit2(form2){
                let params={
                    "eventid":form2.eventid,
                    "topic":form2.topic,
                    "begintime":form2.begintime,
                    "endtime":form2.endtime,
                    "cycmode":form2.cycmode,
                    "location":form2.location,
                    "remindtime":form2.remindtime,
                    "remindway":form2.remindway,
                    "notes":form2.notes,
                }
                this.$http.post("/demo/calendar/update",params,{emulateJSON:true}).then(
                    success=>{
                        this.$message({
                            type:'success',
                            message:'修改成功'
                        })
                        this.dialogFormVisibleed2=false
                        this.xuanran()
                    }
                )
            },
            add(){
                this.dialogFormVisibleed1=true
            },
        }
    }
</script>

<style scoped>

</style>
