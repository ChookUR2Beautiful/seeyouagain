<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<input type="hidden" name="issueType" value="0"/>
 <div id="tab4" class="tab-pane">
      <ul id="myTab1" class="nav nav-tabs">
       <h5>选择用户类型:</h5>
	      <li class="active">
	        <a href="#5tab11" data-toggle="tab">会员用户</a>
	      </li>
	      <li class="">
	        <a href="#5tab12" data-toggle="tab">向蜜客</a>
	      </li>
	      <li class="">
	        <a href="#5tab13" data-toggle="tab">商家</a>
	      </li>
	      <li class="">
	        <a href="#5tab14" data-toggle="tab">合作商</a>
	      </li>
	      <li class="">
	        <a href="#5tab15" data-toggle="tab">非注册会员</a>
	      </li>
	    </ul>
	    <div class="tab-content">
	       <div id="5tab11" class="tab-pane in active">
	           <div class="panel">
				<div class="panel-body">
				<table>
					<tr><td>
					<form class="form-horizontal" role="form"  id="membereForm">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:100px;"><h5>昵称:</h5></td>
									<td style="width:400px;"> <input type="text" class="form-control"  name="name" style="width:90%;"> </td>
									<td style="width:100px;"><h5>手机号:</h5></td>
									<td style="width:400px;"><input type="text" class="form-control" name="phone" style="width:90%;"></td>
									<td colspan="6"><button type="button" id="memberequery" class="btn btn-default" >&nbsp;查询</button>
									</td>
								</tr>
								<tr>
								   <td colspan="5">
									<div class="panel panel-default">
										<div id="membereList"></div>
								    </div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					</td></tr>
					<tr><td>
						<form class="form-horizontal" role="form"  id="orderQuestioneForm" dependentForm="membereForm">
						 	<input type="hidden" name="issueType" value="3"/>
							<input type="hidden" id="userType" name="userType" value="0"/>
							<table style="width:100%;">
								<tbody>
									<tr>
									   <td style="width:110px;"><h5>客服问题记录:</h5></td>
									   <td colspan="4"> <textarea name=issueRecord rows="6" class="form-control" ></textarea></td>
									</tr>
									<tr>
									   <td style="width:110px;"><h5>发到中管办：</h5></td>
									   <td colspan="4">
										<input type="radio"  name="isSend" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio"  name="isSend" value="0" checked="checked">&nbsp;否 
										</td>
									</tr>
									<tr>
									    <td style="width:110px;"><h5>呼入时间记录：</h5></td>
								    	<td colspan="4"> <input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="dateCreate" style="width:20%;";float:left">
								        </td>
									</tr>
									<tr>
									   <td style="width:110px;"><h5>处理结果：</h5></td>
									   <td colspan="4">
										 <input type="radio" name="result" value="1">&nbsp;已处理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="result" value="0" checked="checked">&nbsp;待处理
										</td>
									</tr>
									<tr style="height:20px"></tr>
									<tr>
									    <th colspan="3" style="text-align: center;"> 
				 						<button type="submit" class="btn btn-success"><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
				 						<input  class="btn btn-default" type="reset" value="重置全部"   data-bus = 'reset' />
				 					    </th>
									</tr>
								</tbody>
							</table>
					</form>
					
					</td></tr>
				</table>
				
				</div>
	          </div> 
	       </div>
	        <div id="5tab12" class="tab-pane">
		       <div class="panel">
					<div class="panel-body">
					<table >
						<tbody>
							<tr><td>
							<form class="form-horizontal" role="form"  id="guesteSearcheForm"> 
						    <table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:100px;"><h5>&nbsp;&nbsp;向蜜客名称:</h5></td>
										<td style="width:300px;"><input type="text" class="form-control"  name="nname" style="width:90%;"></td>
										<td style="width:100px;"><h5>&nbsp;&nbsp;手机号:</h5></td>
										<td style="width:300px;"><input type="text" class="form-control"   name="phone" style="width:90%;"></td>
										<td colspan="6"><button type="button" id="guesteMemberquery" class="btn btn-default" >&nbsp;查询</button>
										</td>
									</tr>
									<tr>
								     <td colspan="5">
										<div class="panel panel-default">
											<div id="guesteMemberList"></div>
									    </div>
										</td>
							    	</tr>
								</tbody>
							</table>
						</form>
							
					</td></tr>
						<tr><td>
						<form class="form-horizontal" role="form"  id="guesteForm" dependentForm = "guestSearcheForm">
							<input type="hidden" name="issueType" value="3"/>
						    <input type="hidden" id="userType" name="userType" value="1"/> 
							<table style="width:100%;">
								<tbody>
									<tr>
									   <td style="width:110px;"><h5>客服问题记录:</h5></td>
								       <td colspan="5">
										<textarea name="issueRecord" rows="6" class="form-control" ></textarea>
										</td>
									</tr>
									<tr>
									   <td style="width:110px;"><h5>发到中管办：</h5></td>
								       <td>
										<input type="radio"  name="isSend" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio"  name="isSend" checked="checked" value="0">&nbsp;否
										</td>
									</tr>
									<tr>
									    <td style="width:110px;"><h5>呼入时间记录：</h5></td>
										<td style="width:160px;">
								    	<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="dateCreate" style="width:20%;float:left">
								        </td>
									</tr>
									<tr>
									   <td style="width:110px;"><h5>处理结果：</h5></td>
								       <td>
										<input type="radio"  name="result" value="1">&nbsp;已处理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio"  name="result" checked="checked" value="0">&nbsp;待处理
										</td>
									</tr>
									<tr style="height:20px"></tr>
									<tr>
									    <th colspan="3" style="text-align: center;"> 
				 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
				 						<input  class="btn btn-default" type="reset" value="重置全部"   data-bus = 'reset' />
				 					    </th>
									</tr>
								</tbody>
							</table>
						</form>
							</td></tr>
						</tbody>
					
					</table>
						
					</div>
		          </div> 
	         </div>
	         <div id="5tab13" class="tab-pane">
		       <div class="panel">
					<div class="panel-body">
						<table style="width:100%;">
						  <tbody>
							<tr><td>
							 <form class="form-horizontal" role="form"  id="merchantSearcheForm">
							 <input type="hidden" name="issueType" value="3"/>
						     <input type="hidden" id="userType" name="userType" value="2"/> 
								<table style="width:100%;">
								<tbody>
									<tr>
										<td style="width:100px;"><h5>&nbsp;&nbsp;商家昵称:</h5></td>
										<td style="width:400px;"><input type="text" class="form-control"  name="sellername" style="width:90%;"></td>
										<td style="width:100px;"><h5>&nbsp;&nbsp;商家手机号:</h5></td>
										<td style="width:400px;"><input type="text" class="form-control"  name="phoneid" style="width:90%;"></td>
										<td colspan="6"><button type="button" id="merchantequery" class="btn btn-default" >&nbsp;查询</button>
										</td>
									</tr>
									<tr>
								   <td colspan="5">
									<div class="panel panel-default">
										<div id="merchanteList"></div>
								    </div>
									</td>
									</tr>
								</tbody>
							</table>
							</form>
							</td></tr>
							<tr><td>
							<form class="form-horizontal" role="form"  id="merchanteForm" dependentForm = "merchantSearcheForm">
								<input type="hidden" name="issueType" value="3"/>
							    <input type="hidden" id="userType" name="userType" value="2"/> 
								<table>
								<tbody>
									<tr>
									   <td style="width:110px;"><h5>客服问题记录:</h5></td>
								       <td colspan="4" style="width:800px;">
										<textarea name="issueRecord" rows="6" class="form-control" ></textarea>
										</td>
									</tr>
									<tr>
									   <td style="width:110px;"><h5>发到中管办：</h5></td>
								       <td>
										<input type="radio"  name="isSend" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio"  name="isSend" checked="checked" value="0">&nbsp;否
										</td>
									</tr>
									<tr>
									    <td style="width:110px;"><h5>呼入时间记录：</h5></td>
										<td style="width:160px;">
								    	<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="dateCreate" style="width:20%;float:left">
								        </td>
									</tr>
									<tr>
									   <td style="width:110px;"><h5>处理结果：</h5></td>
								       <td>
										<input type="radio"  name="result" value="1">&nbsp;已处理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio"  name="result" checked="checked" value="0">&nbsp;待处理
										</td>
									</tr>
									<tr style="height:20px"></tr>
									<tr>
									    <th colspan="3" style="text-align: center;"> 
				 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
				 						<input  class="btn btn-default" type="reset" value="重置全部"   data-bus = 'reset' />
				 					    </th>
									</tr>
								</tbody>
							</table>
							</form>
						</td></tr>
						</table>
							
					</div>
		          </div> 
	         </div>
	         <div id="5tab14" class="tab-pane">
		       <div class="panel">
					<div class="panel-body">
					<table style="width:100%;">
					<tbody>
					<tr><td>
					  <form class="form-horizontal" role="form"  id="cooperationSearcheForm">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td style="width:100px;"><h5>&nbsp;&nbsp;法人姓名:</h5></td>
									<td style="width:220px;"><input type="text" class="form-control"  name="legalperson" style="width:90%;"></td>
							    	<td style="width:100px;"><h5>&nbsp;&nbsp;手机号:</h5></td>
									<td style="width:220px;"><input type="text" class="form-control"  name="phoneid" style="width:90%;"></td>
									<td style="width:100px;"><h5>&nbsp;&nbsp;企业名称:</h5></td>
									<td style="width:220px;"><input type="text" class="form-control"  name="corporate" style="width:90%;"></td>
									<td colspan="6"><button type="button" id="cooperationequery" class="btn btn-default" >&nbsp;查询</button></td>
								</tr>
								<tr>
								   <td colspan="8">
									<div class="panel panel-default">
										<div id="cooperationeList"></div>
								    </div>
									</td>
								</tr>
							</tbody>
							</table>
						</form>
					
					</td></tr>
					<tr><td>
					<form class="form-horizontal" role="form"  id="cooperationeForm" dependentForm = "cooperationSearcheForm">
							<input type="hidden" name="issueType" value="3"/>
						    <input type="hidden" id="userType" name="userType" value="3"/> 
						   	<table >
							<tbody>
								<tr>
								   <td style="width:110px;"><h5>客服问题记录:</h5></td>
							       <td colspan="6" style="width:900px;" >
									<textarea name="issueRecord" rows="6" class="form-control" ></textarea>
									</td>
								</tr>
								<tr>
								   <td style="width:110px;"><h5>发到中管办：</h5></td>
							       <td>
									<input type="radio"  name="isSend" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="isSend" checked="checked" value="0">&nbsp;否
									</td>
								</tr>
								<tr>
								    <td style="width:110px;"><h5>呼入时间记录：</h5></td>
									<td style="width:100px;">
							    	<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="dateCreate" style="width:20%;float:left">
							        </td>
								</tr>
								<tr>
								   <td style="width:110px;"><h5>处理结果：</h5></td>
							       <td>
									<input type="radio"  name="result" value="1">&nbsp;已处理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="result" checked="checked" value="0">&nbsp;待处理
									</td>
								</tr>
								<tr style="height:20px"></tr>
								<tr>
								    <th colspan="3" style="text-align: center;"> 
			 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
			 						<input  class="btn btn-default" type="reset" value="重置全部"   data-bus = 'reset' />
			 					    </th>
								</tr>
							</tbody>
							</table>
						</form>
					</td></tr>
					</tbody>
					</table>
					</div>
		          </div> 
	       </div>
	         <div id="5tab15" class="tab-pane">
		       <div class="panel">
					<div class="panel-body">
					<table>
					<tbody>
					<tr><td>
					 <form class="form-horizontal" role="form"  id="noMemberSearcheForm">
							<table style="width:100%;">
								<tbody>
									<tr>
								        <td style="width:100px;"><h5>&nbsp;&nbsp;姓名:</h5></td>
										<td style="width:400px;"><input type="text" class="form-control"  name="name" style="width:90%;"></td>
										<td style="width:100px;"><h5>&nbsp;&nbsp;手机号:</h5></td>
										<td style="width:400px;"><input type="text" class="form-control"  name="phone" style="width:90%;"></td>
										<!-- <td colspan="6"><button type="button" id="memberquery" class="btn btn-default" >&nbsp;查询</button></td> -->
									</tr>
								</tbody>
							</table>
						</form>
					</td></tr>
					<tr><td>
					   <form class="form-horizontal" role="form"  id="noMembereForm" dependentForm = "noMemberSearcheForm">
							<input type="hidden" name="issueType" value="3"/>
						    <input type="hidden" id="userType" name="userType" value="4"/> 
							<table style="width:100%;">
								<tbody>
								<tr>
								   <td style="width:110px;"><h5>客服问题记录:</h5></td>
							       <td colspan="3">
									<textarea name="issueRecord" rows="6" class="form-control" ></textarea>
									</td>
								</tr>
								<tr>
								   <td style="width:110px;"><h5>发到中管办：</h5></td>
							       <td>
									<input type="radio"  name="isSend" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="isSend" checked="checked" value="0">&nbsp;否
									</td>
								</tr>
								<tr>
								    <td style="width:110px;"><h5>呼入时间记录：</h5></td>
									<td style="width:100px;">
							    	<input type="text" placeholder="yyyy-MM-dd hh:mm" class="form-control form_datetime" name="dateCreate" style="width:20%;float:left">
							        </td>
								</tr>
								<tr>
								   <td style="width:110px;"><h5>处理结果：</h5></td>
							       <td>
									<input type="radio"  name="result" value="1">&nbsp;已处理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"  name="result" checked="checked" value="0">&nbsp;待处理
									</td>
								</tr>
								<tr style="height:20px"></tr>
								<tr>
								    <th colspan="3" style="text-align: center;"> 
			 						<button type="submit" class="btn btn-success" ><span class="icon-ok"></span>  保  存  </button>&nbsp;&nbsp;
			 						<input  class="btn btn-default" type="reset" value="重置全部"   data-bus = 'reset' />
			 					    </th>
								</tr>
							</tbody>
							</table>
						</form>
					</td></tr>
					</tbody>
					</table>
					</div>
		          </div> 
	         </div>
	    </div>
	</div>
