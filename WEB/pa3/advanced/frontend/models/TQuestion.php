<?php

namespace frontend\models;

use Yii;

/**
 * This is the model class for table "t_question".
 *
 * @property integer $question_id
 * @property integer $checkpoint_id
 * @property integer $challenge_type_id
 * @property integer $location_id
 * @property string $question
 *
 * @property TAnswer[] $tAnswers
 * @property TCheckpoint $checkpoint
 * @property TChallengeType $challengeType
 * @property TLocation $location
 */
class TQuestion extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 't_question';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['challenge_type_id', 'checkpoint_id', 'no_soal'], 'integer'],
            [['question'], 'string'],
            [['option1', 'option2', 'option3', 'option4', 'answer'], 'string', 'max' => 64],
            [['challenge_type_id'], 'exist', 'skipOnError' => true, 'targetClass' => TChallengeType::className(), 'targetAttribute' => ['challenge_type_id' => 'challenge_type_id']],
            [['checkpoint_id'], 'exist', 'skipOnError' => true, 'targetClass' => TCheckpoint::className(), 'targetAttribute' => ['checkpoint_id' => 'checkpoint_id']],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'question_id' => 'Question ID',
            'challenge_type_id' => 'Challenge Type ID',
            'question' => 'Question',
            'checkpoint_id' => 'Checkpoint ID',
            'no_soal' => 'Nomor Soal',
            'option1' => 'Option 1',
            'option2' => 'Option 2',
            'option3' => 'Option 3',
            'option4' => 'Option 4',
            'answer' => 'Answer',
        ];
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getChallengeType()
    {
        return $this->hasOne(TChallengeType::className(), ['challenge_type_id' => 'challenge_type_id']);
    }

    /**
     * @return \yii\db\ActiveQuery
     */
    public function getTQuestions()
    {
        return $this->hasMany(TQuestion::className(), ['checkpoint_id' => 'checkpoint_id']);
    }
}
