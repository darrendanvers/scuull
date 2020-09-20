--
-- Loads sample data from a job I've been writing locally.
--

--
-- BATCH_JOB_INSTANCE
-- 
INSERT INTO public.batch_job_instance (job_instance_id, version, job_name, job_key) VALUES (1, 0, 'INTEGRATION-DEMO', 'f6244c50cade8836ffd4bd0419c1af99');
INSERT INTO public.batch_job_instance (job_instance_id, version, job_name, job_key) VALUES (2, 0, 'INTEGRATION-DEMO', 'd1d713ccd683f29b8efd2f94afca2147');
INSERT INTO public.batch_job_instance (job_instance_id, version, job_name, job_key) VALUES (3, 0, 'INTEGRATION-DEMO', '8985bd8511d79b52367aec3c3fd3585e');
INSERT INTO public.batch_job_instance (job_instance_id, version, job_name, job_key) VALUES (4, 0, 'INTEGRATION-DEMO', '8cf5d438879f7d8f50146b50a2477d47');
INSERT INTO public.batch_job_instance (job_instance_id, version, job_name, job_key) VALUES (5, 0, 'INTEGRATION-DEMO', '41fa39316418fe82860f24a7479fcc17');

--
-- BATCH_JOB_EXECUTION
-- 
INSERT INTO public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated, job_configuration_location) VALUES (1, 2, 1, '2020-09-20 12:01:43.469000', '2020-09-20 12:01:43.508000', '2020-09-20 12:02:30.077000', 'COMPLETED', 'COMPLETED', '', '2020-09-20 12:02:30.077000', null);
INSERT INTO public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated, job_configuration_location) VALUES (2, 2, 2, '2020-09-20 12:03:10.348000', '2020-09-20 12:03:10.381000', '2020-09-20 12:03:56.241000', 'COMPLETED', 'COMPLETED', '', '2020-09-20 12:03:56.241000', null);
INSERT INTO public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated, job_configuration_location) VALUES (3, 2, 3, '2020-09-20 12:04:06.514000', '2020-09-20 12:04:06.551000', '2020-09-20 12:04:54.487000', 'COMPLETED', 'COMPLETED', '', '2020-09-20 12:04:54.487000', null);
INSERT INTO public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated, job_configuration_location) VALUES (4, 2, 4, '2020-09-20 12:06:00.044000', '2020-09-20 12:06:00.078000', '2020-09-20 12:06:32.958000', 'FAILED', 'FAILED', '', '2020-09-20 12:06:32.958000', null);
INSERT INTO public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated, job_configuration_location) VALUES (5, 2, 4, '2020-09-20 12:07:30.591000', '2020-09-20 12:07:30.627000', '2020-09-20 12:07:43.016000', 'COMPLETED', 'COMPLETED', '', '2020-09-20 12:07:43.016000', null);
INSERT INTO public.batch_job_execution (job_execution_id, version, job_instance_id, create_time, start_time, end_time, status, exit_code, exit_message, last_updated, job_configuration_location) VALUES (6, 2, 5, '2020-09-20 12:10:06.667000', '2020-09-20 12:10:06.711000', '2020-09-20 12:10:55.722000', 'COMPLETED', 'COMPLETED', '', '2020-09-20 12:10:55.722000', null);

--
-- BATCH_JOB_EXECUTION_PARAMS
-- 
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (1, 'STRING', 'batchId', '1d461daf-9eac-4c53-a1f5-60c83cad9258', '1969-12-31 18:00:00.000000', 0, 0, 'Y');
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (2, 'STRING', 'batchId', 'e466aa8b-5676-4250-a79e-efbb08953890', '1969-12-31 18:00:00.000000', 0, 0, 'Y');
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (3, 'STRING', 'batchId', 'c4018d90-0c20-418f-bc5b-e8d84778bc11', '1969-12-31 18:00:00.000000', 0, 0, 'Y');
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (4, 'STRING', 'batchId', 'a0094188-5f99-4712-be82-7b9df421aa36', '1969-12-31 18:00:00.000000', 0, 0, 'Y');
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (5, 'STRING', 'batchId', 'a0094188-5f99-4712-be82-7b9df421aa36', '1969-12-31 18:00:00.000000', 0, 0, 'Y');
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (6, 'STRING', 'batchId', '0fd51aa6-fbbe-4923-95cd-24e7898d7765', '1969-12-31 18:00:00.000000', 0, 0, 'Y');
INSERT INTO public.batch_job_execution_params (job_execution_id, type_cd, key_name, string_val, date_val, long_val, double_val, identifying) VALUES (6, 'STRING', 'runId', '100', '1969-12-31 18:00:00.000000', 0, 0, 'Y');

--
-- BATCH_STEP_EXECUTION
-- 
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (5, 23, 'LOAD-ALBUM', 2, '2020-09-20 12:03:10.557000', '2020-09-20 12:03:42.292000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:03:42.292000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (1, 3, 'LOAD-BATCH', 1, '2020-09-20 12:01:43.533000', '2020-09-20 12:01:43.677000', 'COMPLETED', 1, 0, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:01:43.677000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (11, 23, 'LOAD-ALBUM', 4, '2020-09-20 12:06:00.272000', '2020-09-20 12:06:32.055000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:06:32.055000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (13, 21, 'LOAD-CORE', 5, '2020-09-20 12:07:30.682000', '2020-09-20 12:07:43.009000', 'COMPLETED', 19, 1831, 0, 1831, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:07:43.009000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (9, 23, 'LOAD-CORE', 3, '2020-09-20 12:04:39.990000', '2020-09-20 12:04:54.480000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:04:54.480000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (3, 23, 'LOAD-CORE', 1, '2020-09-20 12:02:16.181000', '2020-09-20 12:02:30.070000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:02:30.070000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (12, 3, 'LOAD-CORE', 4, '2020-09-20 12:06:32.077000', '2020-09-20 12:06:32.951000', 'FAILED', 1, 200, 0, 100, 0, 0, 0, 1, 'FAILED', 'java.lang.IllegalStateException: job should fail.
	at dev.codestijl.integrationdemo.loadcore.AlbumWriter.write(AlbumWriter.java:48)
	at jdk.internal.reflect.GeneratedMethodAccessor39.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.support.DelegatingIntroductionInterceptor.doProceed(DelegatingIntroductionInterceptor.java:136)
	at org.springframework.aop.support.DelegatingIntroductionInterceptor.invoke(DelegatingIntroductionInterceptor.java:124)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
	at com.sun.proxy.$Proxy47.write(Unknown Source)
	at org.springframework.batch.item.support.CompositeItemWriter.write(CompositeItemWriter.java:59)
	at jdk.internal.reflect.GeneratedMethodAccessor39.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.support.DelegatingIntroductionInterceptor.doProceed(DelegatingIntroductionInterceptor.java:136)
	at org.springframework.aop.support.DelegatingIntroductionInterceptor.invoke(DelegatingIntroductionInterceptor.java:124)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
	at com.sun.proxy.$Proxy50.write(Unknown Source)
	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)
	at org.springframework.batch.', '2020-09-20 12:06:32.951000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (4, 3, 'LOAD-BATCH', 2, '2020-09-20 12:03:10.405000', '2020-09-20 12:03:10.534000', 'COMPLETED', 1, 0, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:03:10.534000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (10, 3, 'LOAD-BATCH', 4, '2020-09-20 12:06:00.103000', '2020-09-20 12:06:00.250000', 'COMPLETED', 1, 0, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:06:00.250000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (14, 3, 'LOAD-BATCH', 6, '2020-09-20 12:10:06.743000', '2020-09-20 12:10:06.902000', 'COMPLETED', 1, 0, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:10:06.903000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (8, 23, 'LOAD-ALBUM', 3, '2020-09-20 12:04:06.761000', '2020-09-20 12:04:39.967000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:04:39.967000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (2, 23, 'LOAD-ALBUM', 1, '2020-09-20 12:01:43.701000', '2020-09-20 12:02:16.160000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:02:16.160000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (15, 23, 'LOAD-ALBUM', 6, '2020-09-20 12:10:06.927000', '2020-09-20 12:10:41.646000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:10:41.646000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (6, 23, 'LOAD-CORE', 2, '2020-09-20 12:03:42.312000', '2020-09-20 12:03:56.234000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:03:56.234000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (7, 3, 'LOAD-BATCH', 3, '2020-09-20 12:04:06.581000', '2020-09-20 12:04:06.738000', 'COMPLETED', 1, 0, 0, 1, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:04:06.739000');
INSERT INTO public.batch_step_execution (step_execution_id, version, step_name, job_execution_id, start_time, end_time, status, commit_count, read_count, filter_count, write_count, read_skip_count, write_skip_count, process_skip_count, rollback_count, exit_code, exit_message, last_updated) VALUES (16, 23, 'LOAD-CORE', 6, '2020-09-20 12:10:41.671000', '2020-09-20 12:10:55.679000', 'COMPLETED', 21, 2031, 0, 2031, 0, 0, 0, 0, 'COMPLETED', '', '2020-09-20 12:10:55.679000');

--
-- BATCH_STEP_EXECUTION_CONTEXT
-- 
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (10, '{"@class":"java.util.HashMap","batch.taskletType":"com.sun.proxy.$Proxy44","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (1, '{"@class":"java.util.HashMap","batch.taskletType":"com.sun.proxy.$Proxy44","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (15, '{"@class":"java.util.HashMap","batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (11, '{"@class":"java.util.HashMap","batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (12, '{"@class":"java.util.HashMap","JdbcCursorItemReader.read.count":100,"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (6, '{"@class":"java.util.HashMap","JdbcCursorItemReader.read.count":2032,"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (13, '{"@class":"java.util.HashMap","JdbcCursorItemReader.read.count":1932,"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (3, '{"@class":"java.util.HashMap","JdbcCursorItemReader.read.count":2032,"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (5, '{"@class":"java.util.HashMap","batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (4, '{"@class":"java.util.HashMap","batch.taskletType":"com.sun.proxy.$Proxy44","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (2, '{"@class":"java.util.HashMap","batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (7, '{"@class":"java.util.HashMap","batch.taskletType":"com.sun.proxy.$Proxy44","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (8, '{"@class":"java.util.HashMap","batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (9, '{"@class":"java.util.HashMap","JdbcCursorItemReader.read.count":2032,"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (14, '{"@class":"java.util.HashMap","batch.taskletType":"com.sun.proxy.$Proxy44","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);
INSERT INTO public.batch_step_execution_context (step_execution_id, short_context, serialized_context) VALUES (16, '{"@class":"java.util.HashMap","JdbcCursorItemReader.read.count":2032,"batch.taskletType":"org.springframework.batch.core.step.item.ChunkOrientedTasklet","batch.stepType":"org.springframework.batch.core.step.tasklet.TaskletStep"}', null);

--
-- BATCH_JOB_EXECUTION_CONTEXT
-- 
INSERT INTO public.batch_job_execution_context (job_execution_id, short_context, serialized_context) VALUES (1, '{"@class":"java.util.HashMap"}', null);
INSERT INTO public.batch_job_execution_context (job_execution_id, short_context, serialized_context) VALUES (2, '{"@class":"java.util.HashMap"}', null);
INSERT INTO public.batch_job_execution_context (job_execution_id, short_context, serialized_context) VALUES (3, '{"@class":"java.util.HashMap"}', null);
INSERT INTO public.batch_job_execution_context (job_execution_id, short_context, serialized_context) VALUES (4, '{"@class":"java.util.HashMap"}', null);
INSERT INTO public.batch_job_execution_context (job_execution_id, short_context, serialized_context) VALUES (5, '{"@class":"java.util.HashMap"}', null);
INSERT INTO public.batch_job_execution_context (job_execution_id, short_context, serialized_context) VALUES (6, '{"@class":"java.util.HashMap"}', null);