<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use kartik\file\FileInput;
use yii\helpers\Url;

/* @var $this yii\web\View */
/* @var $model frontend\models\TLocation */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tlocation-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'location_name')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'city_name')->textInput(['maxlength' => true]) ?>

    <!--?= $form->field($model, 'path_gambar')->widget(FileInput::classname(), [
    	'name' => 'attachment_48[]',
    	'options'=>[
        	'multiple'=>true
        ],
    	'pluginOptions' => [
        	'uploadUrl' => Url::to(['/site/img']),
        'maxFileCount' => 99
    	]
	]); ?-->
	<?= $form->field($model, 'path_gambar')->fileinput() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
